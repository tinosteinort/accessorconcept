package de.tse.accessorconcept._02_only_write_interface;

import de.tse.accessorconcept.ByteArrayHelper;

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