package de.tse.accessorconcept._03_read_and_write_interface.reader;

import de.tse.accessorconcept.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public class StringAttributeReader implements AttributeReader<String> {

    @Override public String read(final byte[] data, final Attribute attribute) {
        return ByteArrayHelper.readString(data, attribute.getIndex(), attribute.getLength());
    }
}
