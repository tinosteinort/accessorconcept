package de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.reader;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class StringAttributeReader implements AttributeReader<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public String read(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute) {
        return data.getString().substring(attribute.getIndex(), attribute.getIndex() + attribute.getLength()).trim();
    }
}
