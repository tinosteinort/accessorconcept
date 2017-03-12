package de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

import java.util.Arrays;

public class StringAttributeWriter implements AttributeWriter<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute, final String value) {

        final String before = data.getString().substring(0, attribute.getIndex());
        final String after = data.getString().substring(attribute.getIndex() + attribute.getLength());
        final String newValue = before + fillOrCut(value, attribute) + after;

        data.update(newValue);
    }

    private String fillOrCut(final String value, final FixedLengthStringAttribute<String> attribute) {
        if (value == null) {
            final char[] data = new char[attribute.getLength()];
            Arrays.fill(data, ' ');
            return String.valueOf(data);
        }
        else if (value.length() < attribute.getLength()) {
            final char[] data = new char[attribute.getLength() - value.length()];
            Arrays.fill(data, ' ');
            return value + String.valueOf(data);
        }
        else {
            return value.substring(0, attribute.getLength());
        }
    }
}