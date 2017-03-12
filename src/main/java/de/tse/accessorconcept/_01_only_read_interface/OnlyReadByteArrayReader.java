package de.tse.accessorconcept._01_only_read_interface;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayHelper;

import java.util.Optional;

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
