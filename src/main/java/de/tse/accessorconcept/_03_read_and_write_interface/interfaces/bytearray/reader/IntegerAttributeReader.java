package de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.reader;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;

public class IntegerAttributeReader implements AttributeReader<byte[], Integer, ByteArrayAttribute<Integer>> {

    @Override public Integer read(final byte[] data, final ByteArrayAttribute attribute) {
        return ByteArrayHelper.readInteger(data, attribute.getIndex(), attribute.getLength());
    }
}
