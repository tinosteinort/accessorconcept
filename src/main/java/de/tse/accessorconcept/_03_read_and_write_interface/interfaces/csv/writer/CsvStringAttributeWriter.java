package de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

public class CsvStringAttributeWriter implements AttributeWriter<CsvData, String, CsvAttribute<String>> {

    @Override public void write(final CsvData data, final CsvAttribute<String> attribute, final String value) {
        data.set(attribute.getIndex(), value);
    }
}
