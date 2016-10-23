package de.tse.accessorconcept._03_read_and_write_interface.reader;

import de.tse.accessorconcept.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public class IntegerAttributeReader implements AttributeReader<Integer> {

    @Override public Integer read(final byte[] data, final Attribute attribute) {
        return ByteArrayHelper.readInteger(data, attribute.getIndex(), attribute.getLength());
    }
}
