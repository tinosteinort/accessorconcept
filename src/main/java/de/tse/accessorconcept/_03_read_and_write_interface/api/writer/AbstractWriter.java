package de.tse.accessorconcept._03_read_and_write_interface.api.writer;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.Attribute;

import java.util.Optional;

public abstract class AbstractWriter<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final TUPEL_TYPE data;

    public AbstractWriter(final TUPEL_TYPE data) {
        this.data = data;
    }

    protected abstract AccessorConfig<TUPEL_TYPE> getConfig();

    public <T> void write(final ATTR_TYPE attribute, final T value) {
        writer(attribute).write(data, attribute, value);
    }

    private <T> AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE> writer(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeWriter<TUPEL_TYPE, T, ATTR_TYPE>) getConfig().writerFor(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Writer available for Type " + attribute.getType()));
    }
}
