package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept._03_read_and_write_interface.writer.AttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.writer.IntegerAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.writer.StringAttributeWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ByteArrayWriter {

    private final byte[] data;

    private final Map<Class<?>, AttributeWriter<?>> readerMapping = new HashMap<>();

    public ByteArrayWriter(final byte[] data) {
        this.data = data;

        readerMapping.put(String.class, new StringAttributeWriter());
        readerMapping.put(Integer.class, new IntegerAttributeWriter());
    }

    public void firstname(final String firstname) {
        write(ByteArrayDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        write(ByteArrayDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        write(ByteArrayDescriptor.AGE, age);
    }

    private <T> void write(final Attribute<T> attribute, final T value) {
        writer(attribute).write(data, attribute, value);
    }

    private <T> AttributeWriter<T> writer(final Attribute<T> attribute) {
        return Optional
                .ofNullable((AttributeWriter<T>) readerMapping.get(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Writer available for Type " + attribute.getType()));
    }
}
