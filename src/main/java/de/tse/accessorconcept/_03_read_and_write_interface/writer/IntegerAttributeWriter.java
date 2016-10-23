package de.tse.accessorconcept._03_read_and_write_interface.writer;

import de.tse.accessorconcept.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public class IntegerAttributeWriter implements AttributeWriter<Integer> {

    @Override public void write(final byte[] data, final Attribute<Integer> attribute, final Integer value) {
        ByteArrayHelper.writeInteger(value, data, attribute.getIndex(), attribute.getLength());
    }
}
