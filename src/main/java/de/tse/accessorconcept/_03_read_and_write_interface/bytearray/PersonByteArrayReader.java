package de.tse.accessorconcept._03_read_and_write_interface.bytearray;

import de.tse.accessorconcept._03_read_and_write_interface.bytearray.reader.ByteArrayReader;

import java.util.Optional;

public class PersonByteArrayReader {

    private final ByteArrayReader reader;

    public PersonByteArrayReader(final ByteArrayReader reader) {
        this.reader = reader;
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(reader.read(ByteArrayDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(reader.read(ByteArrayDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(reader.read(ByteArrayDescriptor.AGE));
    }
}
