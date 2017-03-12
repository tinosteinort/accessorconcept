package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept.MyObject;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfigBuilder;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.reader.IntegerAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.reader.RightAlignedIntegerAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.reader.StringAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer.IntegerAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer.RightAlignedIntegerAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer.StringAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.using.fixedlengthstring.PersonFixedLengthStringReader;
import de.tse.accessorconcept._03_read_and_write_interface.using.fixedlengthstring.PersonFixedLengthStringWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ReadFixedLenghtStringInterface {

    private final AccessorConfig<FixedLengthString> config = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
            .build();

    @Test public void testImport() throws UnsupportedEncodingException {

        final String dataFromFile = "Dagobert  Duck      100";

        final MyObject myObject = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(100, myObject.getAge());
    }

    public MyObject doImport(final String data) {

        final MyObject obj = new MyObject();

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(config, new FixedLengthString(data));

        reader.firstname().ifPresent(obj::setFirstname);
        reader.lastname().ifPresent(obj::setLastname);
        reader.age().ifPresent(obj::setAge);

        return obj;
    }

    @Test public void testExport() throws UnsupportedEncodingException {

        final MyObject obj = new MyObject();
        obj.setFirstname("Dagobert");
        obj.setLastname("Duck");
        obj.setAge(100);

        Assert.assertEquals(
                new FixedLengthString("Dagobert  Duck      100"),
                doExport(obj));
    }

    public FixedLengthString doExport(final MyObject obj) {

        final FixedLengthString row = new FixedLengthString(23, ' ');

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(config, row);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return row;
    }

    @Test public void testImportCustomAttribute() {

        final AccessorConfig<FixedLengthString> localConfig = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>(config)
                .registerReader(Integer.class, new RightAlignedIntegerAttributeReader()) // override default behaviour
                .build();

        // Age is right aligned, custom AttributeReader required for reading
        final String dataFromFile = "Tick      Duck        7";

        final MyObject myObject = new MyObject();

        final PersonFixedLengthStringReader reader = new PersonFixedLengthStringReader(localConfig, new FixedLengthString(dataFromFile));

        reader.firstname().ifPresent(myObject::setFirstname);
        reader.lastname().ifPresent(myObject::setLastname);
        reader.age().ifPresent(myObject::setAge);

        Assert.assertEquals("Tick", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(7, myObject.getAge());
    }

    @Test public void testExportCustomAttribute() {

        final AccessorConfig<FixedLengthString> localConfig = new AccessorConfigBuilder<FixedLengthString, FixedLengthStringAttribute<?>>(config)
                .registerWriter(Integer.class, new RightAlignedIntegerAttributeWriter()) // override default behaviour
                .build();

        final MyObject obj = new MyObject();
        obj.setFirstname("Tick");
        obj.setLastname("Duck");
        // Age should be right aligned, custom AttributeWriter required for writing
        obj.setAge(7);

        final FixedLengthString row = new FixedLengthString(23, ' ');

        final PersonFixedLengthStringWriter writer = new PersonFixedLengthStringWriter(localConfig, row);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        Assert.assertEquals(
                new FixedLengthString("Tick      Duck        7"),
                row);
    }
}
