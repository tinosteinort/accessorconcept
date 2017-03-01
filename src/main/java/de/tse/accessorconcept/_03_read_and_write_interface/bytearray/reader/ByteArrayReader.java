package de.tse.accessorconcept._03_read_and_write_interface.bytearray.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AbstractReader;
import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayAttribute;

public class ByteArrayReader extends AbstractReader<byte[], ByteArrayAttribute<?>> {

    private final AccessorConfig<byte[]> config;

    public ByteArrayReader(final AccessorConfig<byte[]> config, final byte[] data) {
        super(data);
        this.config = config;
    }

    @Override protected AccessorConfig<byte[]> getConfig() {
        return config;
    }
}
