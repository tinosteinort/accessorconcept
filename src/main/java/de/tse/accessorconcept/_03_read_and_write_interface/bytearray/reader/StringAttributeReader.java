package de.tse.accessorconcept._03_read_and_write_interface.bytearray.reader;

import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AttributeReader;

public class StringAttributeReader implements AttributeReader<byte[], String, ByteArrayAttribute<String>> {

    @Override public String read(final byte[] data, final ByteArrayAttribute attribute) {
        return ByteArrayHelper.readString(data, attribute.getIndex(), attribute.getLength());
    }
}
