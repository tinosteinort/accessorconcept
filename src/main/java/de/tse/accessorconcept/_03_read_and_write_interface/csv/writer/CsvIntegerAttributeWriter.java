package de.tse.accessorconcept._03_read_and_write_interface.csv.writer;

import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvIntegerAttributeWriter implements AttributeWriter<CsvRow, Integer, CsvAttribute<Integer>> {

    @Override public void write(final CsvRow data, final CsvAttribute<Integer> attribute, final Integer value) {
        data.set(attribute.getIndex(), String.valueOf(value));
    }
}
