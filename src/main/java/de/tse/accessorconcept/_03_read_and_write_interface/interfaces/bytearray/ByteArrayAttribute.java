package de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.Attribute;

public class ByteArrayAttribute<T> implements Attribute<T> {

    private final Class<T> type;
    private final int index;
    private final int length;

    public ByteArrayAttribute(final Class<T> type, final int index, final int length) {
        this.type = type;
        this.index = index;
        this.length = length;
    }

    @Override public Class<T> getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }
}
