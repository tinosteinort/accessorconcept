package de.tse.accessorconcept._03_read_and_write_interface.bytearray.writer;

import de.tse.accessorconcept._03_read_and_write_interface.api.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AbstractWriter;
import de.tse.accessorconcept._03_read_and_write_interface.bytearray.ByteArrayAttribute;

public class ByteArrayWriter extends AbstractWriter<byte[], ByteArrayAttribute<?>> {

    private final AccessorConfig<byte[]> config;

    public ByteArrayWriter(final AccessorConfig<byte[]> config, final byte[] data) {
        super(data);
        this.config = config;
    }

    @Override protected AccessorConfig<byte[]> getConfig() {
        return config;
    }
}
