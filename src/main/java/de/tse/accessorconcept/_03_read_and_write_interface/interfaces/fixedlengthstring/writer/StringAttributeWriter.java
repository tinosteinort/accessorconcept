package de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.StringFitter;

public class StringAttributeWriter implements AttributeWriter<FixedLengthString, String, FixedLengthStringAttribute<String>> {

    private final StringFitter stringFitter = new StringFitter(StringFitter.Alignment.LEFT);

    @Override public void write(final FixedLengthString data, final FixedLengthStringAttribute<String> attribute, final String value) {

        final String before = data.getString().substring(0, attribute.getIndex());
        final String after = data.getString().substring(attribute.getIndex() + attribute.getLength());
        final String newValue = before + stringFitter.fit(value, attribute.getLength()) + after;

        data.update(newValue);
    }
}
