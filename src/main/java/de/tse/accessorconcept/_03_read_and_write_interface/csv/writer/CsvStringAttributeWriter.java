package de.tse.accessorconcept._03_read_and_write_interface.csv.writer;

import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvStringAttributeWriter implements AttributeWriter<CsvRow, String, CsvAttribute<String>> {

    @Override public void write(final CsvRow data, final CsvAttribute<String> attribute, final String value) {
        data.set(attribute.getIndex(), value);
    }
}
