package de.tse.accessorconcept._03_read_and_write_interface.api;

import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AttributeWriter;

import java.util.HashMap;
import java.util.Map;

public class AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    private final Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readers = new HashMap<>();
    private final Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writers = new HashMap<>();

    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerReader(final Class<T> type,
            final AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> reader) {
        readers.put(type, reader);
        return this;
    }
    public <T> AccessorConfigBuilder<TUPEL_TYPE, ATTRIBUTE_DESCRIPTION_TYPE> registerWriter(final Class<T> type,
            final AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writer) {
        writers.put(type, writer);
        return this;
    }

    public AccessorConfigImpl<TUPEL_TYPE> build() {
        return new AccessorConfigImpl<>(readers, writers);
    }

    private class AccessorConfigImpl<TYPE> implements AccessorConfig<TYPE> {

        private final Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readers = new HashMap<>();
        private final Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writers = new HashMap<>();

        private AccessorConfigImpl(
                Map<Class<?>, AttributeReader<TYPE, ?, ? extends Attribute<?>>> readers,
                Map<Class<?>, AttributeWriter<TYPE, ?, ? extends Attribute<?>>> writers) {
            this.readers.putAll(readers);
            this.writers.putAll(writers);
        }

        @Override public <T> AttributeReader<TYPE, T, ? extends Attribute<T>> readerFor(final Class<T> cls) {
            return (AttributeReader<TYPE, T, ? extends Attribute<T>>) readers.get(cls);
        }

        @Override public <T> AttributeWriter<TYPE, T, ? extends Attribute<T>> writerFor(final Class<T> cls) {
            return (AttributeWriter<TYPE, T, ? extends Attribute<T>>) writers.get(cls);
        }
    }
}
