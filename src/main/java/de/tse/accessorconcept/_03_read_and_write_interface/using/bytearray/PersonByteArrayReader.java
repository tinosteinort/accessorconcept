package de.tse.accessorconcept._03_read_and_write_interface.using.bytearray;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.ReadAccessor;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayAttribute;

import java.util.Optional;

public class PersonByteArrayReader {

    private final ReadAccessor<byte[], ByteArrayAttribute<?>> accessor;

    public PersonByteArrayReader(final AccessorConfig<byte[]> config, final byte[] data) {
        this.accessor = new ReadAccessor<>(config, data);
    }

    public Optional<String> firstname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.FIRST_NAME));
    }

    public Optional<String> lastname() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.LAST_NAME));
    }

    public Optional<Integer> age() {
        return Optional.ofNullable(accessor.read(PersonDescriptor.AGE));
    }
}
