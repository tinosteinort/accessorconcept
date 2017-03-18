package de.tse.accessorconcept._03_read_and_write_interface.using.fixedlengthstring;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.ReadAccessor;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

import java.util.Optional;

public class PersonFixedLengthStringReader {

    private final ReadAccessor<FixedLengthString, FixedLengthStringAttribute<?>> accessor;

    public PersonFixedLengthStringReader(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config,
            final FixedLengthString data) {
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
