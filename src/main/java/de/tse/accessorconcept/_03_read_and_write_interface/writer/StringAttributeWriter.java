package de.tse.accessorconcept._03_read_and_write_interface.writer;

import de.tse.accessorconcept.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public class StringAttributeWriter implements AttributeWriter<String> {

    @Override public void write(final byte[] data, final Attribute<String> attribute, final String value) {
        ByteArrayHelper.writeString(value, data, attribute.getIndex(), attribute.getLength());
    }
}
