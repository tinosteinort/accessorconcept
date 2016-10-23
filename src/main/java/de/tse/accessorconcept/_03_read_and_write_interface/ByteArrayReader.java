package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept._03_read_and_write_interface.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.reader.IntegerAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.reader.StringAttributeReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ByteArrayReader {

    private final byte[] data;

    private final Map<Class<?>, AttributeReader<?>> readerMapping = new HashMap<>();

    public ByteArrayReader(final byte[] data) {
        this.data = data;

        readerMapping.put(String.class, new StringAttributeReader());
        readerMapping.put(Integer.class, new IntegerAttributeReader());
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(read(ByteArrayDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(read(ByteArrayDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(read(ByteArrayDescriptor.AGE));
    }

    private <T> T read(final Attribute<T> attribute) {
        return reader(attribute).read(data, attribute);
    }

    private <T> AttributeReader<T> reader(final Attribute<T> attribute) {
        return Optional
                .ofNullable((AttributeReader<T>) readerMapping.get(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute.getType()));
    }
}
