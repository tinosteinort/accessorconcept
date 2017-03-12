package de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv;

import java.util.ArrayList;
import java.util.List;

public class CsvData {

    private final List<String> data = new ArrayList<>();

    public CsvData(final String... data) {
        for (String s : data) {
            this.data.add(s);
        }
    }

    public CsvData(final List<String> data) {
        for (String s : data) {
            this.data.add(s);
        }
    }

    public CsvData(final int columns) {
        for (int i = 0; i < columns; i++) {
            data.add("");
        }
    }

    public int size() {
        return data.size();
    }

    public String get(final int index) {
        return data.get(index);
    }

    public void set(final int index, final String s) {
        data.set(index, s);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        CsvData csvData = (CsvData) o;

        return data.equals(csvData.data);
    }

    @Override public int hashCode() {
        return data.hashCode();
    }
}
