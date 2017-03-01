package de.tse.accessorconcept._03_read_and_write_interface.api.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.Attribute;

import java.util.Optional;

public abstract class AbstractReader<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final TUPEL_TYPE data;

    public AbstractReader(final TUPEL_TYPE data) {
        this.data = data;
    }

    protected abstract AccessorConfig<TUPEL_TYPE> getConfig();

    public <T> T read(final ATTR_TYPE attribute) {
        return (T) reader(attribute).read(data, attribute);
    }

    private <T> AttributeReader<TUPEL_TYPE, T, ATTR_TYPE> reader(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeReader<TUPEL_TYPE, T, ATTR_TYPE>) getConfig().readerFor(attribute.getType()))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute.getType()));
    }
}
