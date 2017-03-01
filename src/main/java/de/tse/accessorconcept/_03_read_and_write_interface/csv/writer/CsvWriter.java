package de.tse.accessorconcept._03_read_and_write_interface.csv.writer;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AbstractWriter;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvWriter extends AbstractWriter<CsvRow, CsvAttribute<?>> {

    private final AccessorConfig<CsvRow> config;

    public CsvWriter(final AccessorConfig<CsvRow> config, final CsvRow data) {
        super(data);
        this.config = config;
    }

    @Override protected AccessorConfig<CsvRow> getConfig() {
        return config;
    }
}
