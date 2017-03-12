package de.tse.accessorconcept._03_read_and_write_interface.using.csv;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.WriteAccessor;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

public class PersonCsvWriter {

    private final WriteAccessor<CsvData, CsvAttribute<?>> accessor;

    public PersonCsvWriter(final AccessorConfig<CsvData> config, final CsvData row) {
        this.accessor = new WriteAccessor<>(config, row);
    }

    public void firstname(final String firstname) {
        accessor.write(PersonDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        accessor.write(PersonDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        accessor.write(PersonDescriptor.AGE, age);
    }
}
