package de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.reader;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class RightAlignedIntegerAttributeReader implements AttributeReader<FixedLengthString, Integer, FixedLengthStringAttribute<Integer>> {

    @Override public Integer read(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute) {
        final String value = data.getString().substring(attribute.getIndex(), attribute.getIndex() + attribute.getLength());
        return Integer.valueOf(value.trim());
    }
}
