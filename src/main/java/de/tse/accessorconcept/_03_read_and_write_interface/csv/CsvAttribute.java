package de.tse.accessorconcept._03_read_and_write_interface.csv;

import de.tse.accessorconcept._03_read_and_write_interface.api.Attribute;

public class CsvAttribute<T> implements Attribute<T> {

    private final Class<T> type;
    private final int index;

    public CsvAttribute(final Class<T> type, final int index) {
        this.type = type;
        this.index = index;
    }

    @Override public Class<T> getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }
}
