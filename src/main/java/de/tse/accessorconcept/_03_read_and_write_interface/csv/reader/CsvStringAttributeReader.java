package de.tse.accessorconcept._03_read_and_write_interface.csv.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.csv.CsvRow;

public class CsvStringAttributeReader implements AttributeReader<CsvRow, String, CsvAttribute<String>> {

    @Override public String read(final CsvRow data, final CsvAttribute<String> attribute) {
        return data.get(attribute.getIndex());
    }
}
