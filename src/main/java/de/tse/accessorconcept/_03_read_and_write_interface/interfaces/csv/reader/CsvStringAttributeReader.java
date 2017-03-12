package de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.reader;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;

public class CsvStringAttributeReader implements AttributeReader<CsvData, String, CsvAttribute<String>> {

    @Override public String read(final CsvData data, final CsvAttribute<String> attribute) {
        return data.get(attribute.getIndex());
    }
}
