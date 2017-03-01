package de.tse.accessorconcept._03_read_and_write_interface.csv;

import de.tse.accessorconcept._03_read_and_write_interface.csv.writer.CsvWriter;

public class PersonCsvWriter {

    private final CsvWriter writer;

    public PersonCsvWriter(final CsvWriter writer) {
        this.writer = writer;
    }

    public void firstname(final String firstname) {
        writer.write(CsvFileDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        writer.write(CsvFileDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        writer.write(CsvFileDescriptor.AGE, age);
    }
}
