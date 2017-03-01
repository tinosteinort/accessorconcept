package de.tse.accessorconcept._03_read_and_write_interface.csv.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AbstractReader;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvReader extends AbstractReader<CsvRow, CsvAttribute<?>> {

    private final AccessorConfig<CsvRow> config;

    public CsvReader(final AccessorConfig<CsvRow> config, final CsvRow data) {
        super(data);
        this.config = config;
    }

    @Override protected AccessorConfig<CsvRow> getConfig() {
        return config;
    }
}
