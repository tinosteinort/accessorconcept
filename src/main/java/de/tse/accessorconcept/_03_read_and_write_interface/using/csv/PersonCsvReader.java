package de.tse.accessorconcept._03_read_and_write_interface.using.csv;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.ReadAccessor;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

import java.util.Optional;

public class PersonCsvReader {

    private final ReadAccessor<CsvData, CsvAttribute<?>> accessor;

    public PersonCsvReader(final AccessorConfig<CsvData, CsvAttribute<?>> config, final CsvData row) {
        this.accessor = new ReadAccessor<>(config, row);
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.AGE));
    }
}
