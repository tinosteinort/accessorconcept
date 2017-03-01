package de.tse.accessorconcept._03_read_and_write_interface.csv.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvIntegerAttributeReader implements AttributeReader<CsvRow, Integer, CsvAttribute<Integer>> {

    @Override public Integer read(final CsvRow data, final CsvAttribute<Integer> attribute) {
        return Integer.valueOf(data.get(attribute.getIndex()));
    }
}
