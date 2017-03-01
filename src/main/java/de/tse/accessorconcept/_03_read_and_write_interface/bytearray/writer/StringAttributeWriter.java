package de.tse.accessorconcept._03_read_and_write_interface.bytearray.writer;

import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AttributeWriter;

public class StringAttributeWriter implements AttributeWriter<byte[], String, ByteArrayAttribute<String>> {

    @Override public void write(final byte[] data, final ByteArrayAttribute<String> attribute, final String value) {
        ByteArrayHelper.writeString(value, data, attribute.getIndex(), attribute.getLength());
    }
}
