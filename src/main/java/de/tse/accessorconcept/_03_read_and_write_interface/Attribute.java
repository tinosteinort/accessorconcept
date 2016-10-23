package de.tse.accessorconcept._03_read_and_write_interface;

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
