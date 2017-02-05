# Zugriff auf Datenstrukturen mit testbarem Code

Der Zugriff auf Datenstrukturen ist nicht immer ganz einfach. Gerade Schnittstellen sollten
 gut getestet werden, da sie die Stabilität mindestens zweier Applikationen beeinflussen.

Ich möchte hier speziell auf Mapping Code eingehen, bei dem das Mapping nicht durch ein Framework
 durchgeführt werden soll oder kann.

Durch diesen Artikel möchte ich zukünfitg Code verhindern, der wie folgt aussieht:

```Java
public MyObject doImport(final byte[] data) {

    final MyObject obj = new MyObject();

    // Die Zahlen entsprechen der Position und Länge der zu lesenden Daten
    obj.setFirstname(ByteArrayHelper.readString(data, 0, 10));
    obj.setLastname(ByteArrayHelper.readString(data, 10, 10));
    obj.setAge(ByteArrayHelper.readInteger(data, 20, 3));
    // ...

    return obj;
}
```

Was ist daran verkehrt?
* Die Ermittlung von `firstname`, `lastname` und `age` sind nicht direkt testbar
* Um in die gleiche Schnittstelle zu schreiben, wird an anderer Stelle exakt die
   gleiche Information von Position und Länge der Attribute benötigt. Das führt
   zu doppelten Code

```Java
public byte[] doExport(final MyObject obj) {

    final byte[] data = new byte[200];

    // Doppelte Angabe der Daten aus der doImport(...) Methode:
    ByteArrayHelper.writeString("Dagobert", data, 0, 10);
    ByteArrayHelper.writeString("Duck", data, 10, 10);
    ByteArrayHelper.writeInteger(100, data, 20, 3);
    // ...

    return data;
}
```

Das kann schnell ausufern und Code entstehen lassen, der schwer zu lesen und zu testen ist.

Durch ein paar Codebeispiele möchte ich Anregungen geben, wie auf solche Schnittstellen
 'testbar' zugegriffen werden kann und dabei auch noch lesbarer Code entsteht.



# byte[] als Beispiel

Als Beispiel nehme ich ein byte[] auf das lesend und schreibend zugegriffen werden soll.
 Anwendungsfall ist z.B. eine Dateischnittstelle, die Datensätze mit fester Länge enthällt.
 Generell lässt sich die Idee hinter den Beispielen aber auch für andere Datenstrukturen als das
 byte[] nutzen. In diesem Beispiel verarbeiten wir also ein byte[] das einen Datensatz einer
 Festlängendatei enthält.


## 01 Nur lesender Zugriff auf eine Schnittstelle

Wollen wir nur lesend auf diese Schnittstelle zugreifen, kann man dieses Konstrukt nutzen:

```Java
public MyObject doImport(byte[] data) {

    final MyObject obj = new MyObject();

    final OnlyReadByteArrayReader reader = new OnlyReadByteArrayReader(data);
    reader.firstname().ifPresent(obj::setFirstname);
    reader.lastname().ifPresent(obj::setLastname);
    reader.age().ifPresent(obj::setAge);

    return obj;
}
```

Welche Vorteile hat dieses Konstrukt? Der Zugriff auf die Attribute findet einzig und allein
 im `OnlyReadByteArrayReader` statt, dessen Methoden alle einzeln geunittestet werden können.
 Nochmal: um zu prüfen ob `firstname` richtig gelesen wird, muss man nur `reader.firstname()`
 testen, und nicht mehr den Umweg über die ganze doImport(...) Methode machen.

Der `OnlyReadByteArrayReader` aus dem Beispiel könnte wie folgt aussehen:

```Java
public class OnlyReadByteArrayReader {

    private final byte[] data;

    public OnlyReadByteArrayReader(final byte[] data) {
        this.data = data;
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(ByteArrayHelper.readString(data, 0, 10));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(ByteArrayHelper.readString(data, 10, 10));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(ByteArrayHelper.readInteger(data, 20, 3));
    }
}
```


## 02 Nur schreibend auf eine Schnittstelle zugreifen

Möchte man auf eine Schnittstelle nur schreibend zugreifen, ohne sie im gleichen Format lesen
 zu wollen, kann man die Struktur des `OnlyReadByteArrayReader` in eine schreibende Schnittstelle
 transformieren:

```Java
public class OnlyWriteByteArrayWriter {

    private final byte[] data;

    public OnlyWriteByteArrayWriter(final byte[] data) {
        this.data = data;
    }

    public void firstname(final String firstname) {
        ByteArrayHelper.writeString(firstname, data, 0, 10);
    }

    public void lastname(final String lastname) {
        ByteArrayHelper.writeString(lastname, data, 10, 10);
    }

    public void age(final int age) {
        ByteArrayHelper.writeInteger(age, data, 20, 3);
    }
}
```

Genutzt wird der Writer so:

```Java
public byte[] doExport(final MyObject obj) {

    final byte[] data = new byte[23];

    final OnlyWriteByteArrayWriter writer = new OnlyWriteByteArrayWriter(data);
    writer.firstname(obj.getFirstname());
    writer.lastname(obj.getLastname());
    writer.age(obj.getAge());

    return data;
}
```

Auch hier ist der Zugriff auf das byte[] druch den `OnlyWriteByteArrayWriter` für sich testbar.
 Es gelten die gleichen Vorzüge wie weiter oben bereits beschrieben.


## 03 Lesend und schreibend auf die gleiche Schnittstelle zugreifen

Möchte man auf die selbe Schnittstelle lesend und schreibnd zugreifen, sollte man nicht einfach
 die beiden Klassen aus den vorherigen Beispielen kombinieren. Denn dann haben wir immernoch
 doppelten Code: die Angaben der Position und der Länge in den lesenden und schreibenden Methoden.
 Die sind sowohl im `OnlyReadByteArrayReader` als auch im `OnlyWriteByteArrayWriter` enthalten.

Bei einer Festlängendatei können wir das Problem z.B. mit einer Beschreibung der Attribute und
 einem 'Descriptor' lösen:

* Datenstruktur schaffen, der die Attribute beschreibt
* Auflistung der Attribute beschreibt die Schnittstelle


```Java
public class Attribute<T> {

    private final Class<T> type;
    private final int index;
    private final int length;

    public Attribute(final Class<T> type, final int index, final int length) {
        this.type = type;
        this.index = index;
        this.length = length;
    }

    public Class<T> getType() {
        return type;
    }
    public int getIndex() {
        return index;
    }
    public int getLength() {
        return length;
    }
}

public class ByteArrayDescriptor {

    public static final Attribute<String> FIRST_NAME = new Attribute<>(String.class, 0, 10);
    public static final Attribute<String> LAST_NAME = new Attribute<>(String.class, 10, 10);
    public static final Attribute<Integer> AGE = new Attribute<>(Integer.class, 20, 3);

    private ByteArrayDescriptor() {

    }
}
```

Der Descriptor stellt durch seine Konstanten nur die Information bereit, wie ein Attribut im byte[] aussieht. Mit diesen Informationen können wir uns
 andere Klassen erstellen, die das byte[] lesen oder schreiben können. Im Descriptor können auch einfach weitere Attribute hinzugefügt werden. In dem
 Beispiel werden bisher nur Strings und Integer gelesen und geschrieben. Damit aber auch noch andere Typen einfach gelesen und geschrieben werden
 können, habe ich jeweils ein neues Interface erstellt:

```Java
public interface AttributeReader<T> {

    T read(byte[] data, Attribute<T> attribute);
}

public interface AttributeWriter<T> {

    void write(byte[] data, Attribute<T> attribute, T value);
}
```

Für jedes dieser Interfaces kann es wiederum mehrere Implementierungen geben. In diesem Beispiel sind es nur die zum Lesen und Schreiben von Strings
 und Integern:

```Java
public class IntegerAttributeReader implements AttributeReader<Integer> {

    @Override public Integer read(final byte[] data, final Attribute attribute) {
        return ByteArrayHelper.readInteger(data, attribute.getIndex(), attribute.getLength());
    }
}

public class StringAttributeReader implements AttributeReader<String> {

    @Override public String read(final byte[] data, final Attribute attribute) {
        return ByteArrayHelper.readString(data, attribute.getIndex(), attribute.getLength());
    }
}

public class IntegerAttributeWriter implements AttributeWriter<Integer> {

    @Override public void write(final byte[] data, final Attribute<Integer> attribute, final Integer value) {
        ByteArrayHelper.writeInteger(value, data, attribute.getIndex(), attribute.getLength());
    }
}

public class StringAttributeWriter implements AttributeWriter<String> {

    @Override public void write(final byte[] data, final Attribute<String> attribute, final String value) {
        ByteArrayHelper.writeString(value, data, attribute.getIndex(), attribute.getLength());
    }
}
```

Nun fehlen nur noch die Reader und Writer Klassen, die die ganzen Klassen bündeln.

```Java
public class ByteArrayReader {

    private final byte[] data;

    private final Map<Class<?>, AttributeReader<?>> readerMapping = new HashMap<>();

    public ByteArrayReader(final byte[] data) {
        this.data = data;

        readerMapping.put(String.class, new StringAttributeReader());
        readerMapping.put(Integer.class, new IntegerAttributeReader());
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(read(ByteArrayDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(read(ByteArrayDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(read(ByteArrayDescriptor.AGE));
    }

    private <T> T read(final Attribute<T> attribute) {
        return reader(attribute).read(data, attribute);
    }

    private <T> AttributeReader<T> reader(final Attribute<T> attribute) {
        return Optional
                .ofNullable((AttributeReader<T>) readerMapping.get(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute.getType()));
    }
}

public class ByteArrayWriter {

    private final byte[] data;

    private final Map<Class<?>, AttributeWriter<?>> readerMapping = new HashMap<>();

    public ByteArrayWriter(final byte[] data) {
        this.data = data;

        readerMapping.put(String.class, new StringAttributeWriter());
        readerMapping.put(Integer.class, new IntegerAttributeWriter());
    }

    public void firstname(final String firstname) {
        write(ByteArrayDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        write(ByteArrayDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        write(ByteArrayDescriptor.AGE, age);
    }

    private <T> void write(final Attribute<T> attribute, final T value) {
        writer(attribute).write(data, attribute, value);
    }

    private <T> AttributeWriter<T> writer(final Attribute<T> attribute) {
        return Optional
                .ofNullable((AttributeWriter<T>) readerMapping.get(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Writer available for Type " + attribute.getType()));
    }
}
```

Zur Vereinfachung werden die AttributeWriter und AttributeReader direkt in den Klassen instanziiert und registriert. Alternativ kann man hier auch
 mit Dependency Injection arbeiten. Um die Idee zu erklären, sollte das aber so ausreichen. Möchte man nun ein neues Attribut mit einem bisher
 unbekannten Typen lesen/schreiben, braucht man nur die Reader/Writer Interfaces für den Typen implementieren und registrieren und die neuen
 Attribute im ByteArrayDescriptor definieren.

# Zusammenspiel von ByteArrayDescriptor, ByteArrayReader und ByteArrayWriter

In diesem Beispiel sieht man das Ergebnis der oben erstellten Klassen:

```Java
public MyObject doImport(final byte[] data) {

    final MyObject obj = new MyObject();

    final ByteArrayReader reader = new ByteArrayReader(data);

    reader.firstname().ifPresent(obj::setFirstname);
    reader.lastname().ifPresent(obj::setLastname);
    reader.age().ifPresent(obj::setAge);

    return obj;
}

public byte[] doExport(final MyObject obj) {

    final byte[] data = new byte[23];

    final ByteArrayWriter writer = new ByteArrayWriter(data);

    writer.firstname(obj.getFirstname());
    writer.lastname(obj.getLastname());
    writer.age(obj.getAge());

    return data;
}
```

Wir haben durch dieses Konstrukt zwar eine ganze Menge Klassen, aber jede Klasse hat eine spezielle Aufgabe. Das erleichtert Tests und die
 Erweiterbarkeit. Außerdem haben wir keinen Code der sich wiederholt, wie z.B. doppelte Angaben der Position und Länge der Daten im byte[].
