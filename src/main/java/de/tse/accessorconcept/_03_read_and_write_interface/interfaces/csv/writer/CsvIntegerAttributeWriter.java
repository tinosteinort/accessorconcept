package de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

public class CsvIntegerAttributeWriter implements AttributeWriter<CsvData, Integer, CsvAttribute<Integer>> {

    @Override public void write(final CsvData data, final CsvAttribute<Integer> attribute, final Integer value) {
        data.set(attribute.getIndex(), String.valueOf(value));
    }
}
