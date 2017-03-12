package de.tse.accessorconcept._03_read_and_write_interface.accessor.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.Attribute;

import java.util.Optional;

public class WriteAccessor<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<TUPEL_TYPE> config;
    private final TUPEL_TYPE data;

    public WriteAccessor(final AccessorConfig<TUPEL_TYPE> config, final TUPEL_TYPE data) {
        this.config = config;
        this.data = data;
    }

    public <T> void write(final ATTR_TYPE attribute, final T value) {
        writer(attribute).write(data, attribute, value);
    }

    private <T> AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE> writer(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE>) config.writerFor(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Writer available for Type " + attribute.getType()));
    }
}
