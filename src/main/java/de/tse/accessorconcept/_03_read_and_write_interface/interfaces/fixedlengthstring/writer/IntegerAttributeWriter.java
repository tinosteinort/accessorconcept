package de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.StringFitter;

public class IntegerAttributeWriter implements AttributeWriter<FixedLengthString, Integer, FixedLengthStringAttribute<Integer>> {

    private final StringFitter stringFitter = new StringFitter(StringFitter.Alignment.LEFT);

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<Integer> attribute, final Integer value) {

        final String before = data.getString().substring(0, attribute.getIndex());
        final String after = data.getString().substring(attribute.getIndex() + attribute.getLength());
        final String newValue = before + fillOrCut(value, attribute) + after;

        data.update(newValue);
    }

    private String fillOrCut(final Integer intValue, final FixedLengthStringAttribute<Integer> attribute) {
        return stringFitter.fit(String.valueOf(intValue), attribute.getLength());
    }
}
