package de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.reader;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

public class CsvIntegerAttributeReader implements AttributeReader<CsvData, Integer, CsvAttribute<Integer>> {

    @Override public Integer read(final CsvData data, final CsvAttribute<Integer> attribute) {
        return Integer.valueOf(data.get(attribute.getIndex()));
    }
}
