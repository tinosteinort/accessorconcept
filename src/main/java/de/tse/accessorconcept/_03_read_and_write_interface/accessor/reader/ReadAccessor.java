package de.tse.accessorconcept._03_read_and_write_interface.accessor.reader;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.Attribute;

import java.util.Optional;

public class ReadAccessor<TUPEL_TYPE, ATTR_TYPE extends Attribute<?>> {

    private final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config;
    private final TUPEL_TYPE data;

    public ReadAccessor(final AccessorConfig<TUPEL_TYPE, ATTR_TYPE> config, final TUPEL_TYPE data) {
        this.config = config;
        this.data = data;
    }

    public <T> T read(final ATTR_TYPE attribute) {
        return (T) reader(attribute).read(data, attribute);
    }

    private <T> AttributeReader<TUPEL_TYPE, T, ATTR_TYPE> reader(final ATTR_TYPE attribute) {
        return Optional
                .ofNullable((AttributeReader<TUPEL_TYPE, T, ATTR_TYPE>) config.readerFor(attribute))
                .orElseThrow(() -> new RuntimeException("No Reader available for Type " + attribute));
    }
}
