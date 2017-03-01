package de.tse.accessorconcept._03_read_and_write_interface.bytearray;

import de.tse.accessorconcept._03_read_and_write_interface.bytearray.writer.ByteArrayWriter;

public class PersonByteArrayWriter {

    private final ByteArrayWriter writer;

    public PersonByteArrayWriter(final ByteArrayWriter writer) {
        this.writer = writer;
    }

    public void firstname(final String firstname) {
        writer.write(ByteArrayDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        writer.write(ByteArrayDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        writer.write(ByteArrayDescriptor.AGE, age);
    }
}
