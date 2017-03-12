package de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.writer;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayHelper;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;

public class IntegerAttributeWriter implements AttributeWriter<byte[], Integer, ByteArrayAttribute<Integer>> {

    @Override public void write(final byte[] data, final ByteArrayAttribute<Integer> attribute, final Integer value) {
        ByteArrayHelper.writeInteger(value, data, attribute.getIndex(), attribute.getLength());
    }
}
