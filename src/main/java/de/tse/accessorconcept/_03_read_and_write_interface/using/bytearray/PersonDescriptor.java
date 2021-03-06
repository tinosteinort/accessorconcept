package de.tse.accessorconcept._03_read_and_write_interface.using.bytearray;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayAttribute;

public class PersonDescriptor {

    public static final ByteArrayAttribute<String> FIRST_NAME = new ByteArrayAttribute<>(String.class, 0, 10);
    public static final ByteArrayAttribute<String> LAST_NAME = new ByteArrayAttribute<>(String.class, 10, 10);
    public static final ByteArrayAttribute<Integer> AGE = new ByteArrayAttribute<>(Integer.class, 20, 3);

    private PersonDescriptor() {

    }
}
