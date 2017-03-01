package de.tse.accessorconcept._03_read_and_write_interface.csv;

import de.tse.accessorconcept._03_read_and_write_interface.csv.reader.CsvReader;

import java.util.Optional;

public class PersonCsvReader {

    private final CsvReader reader;

    public PersonCsvReader(final CsvReader reader) {
        this.reader = reader;
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(reader.read(CsvFileDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(reader.read(CsvFileDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(reader.read(CsvFileDescriptor.AGE));
    }
}
