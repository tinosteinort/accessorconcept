package de.tse.accessorconcept._03_read_and_write_interface;

public class ByteArrayDescriptor {

    public static final Attribute<String> FIRST_NAME = new Attribute<>(String.class, 0, 10);
    public static final Attribute<String> LAST_NAME = new Attribute<>(String.class, 10, 10);
    public static final Attribute<Integer> AGE = new Attribute<>(Integer.class, 20, 3);

    private ByteArrayDescriptor() {

    }
}
