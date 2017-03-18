package de.tse.accessorconcept._03_read_and_write_interface.using.fixedlengthstring;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.WriteAccessor;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthString;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class PersonFixedLengthStringWriter {

    private final WriteAccessor<FixedLengthString, FixedLengthStringAttribute<?>> accessor;

    public PersonFixedLengthStringWriter(
            final AccessorConfig<FixedLengthString, FixedLengthStringAttribute<?>> config,
            final FixedLengthString data) {
        this.accessor = new WriteAccessor<>(config, data);
    }

    public void firstname(final String firstname) {
        accessor.write(PersonDescriptor.FIRST_NAME, firstname);
    }

    public void lastname(final String lastname) {
        accessor.write(PersonDescriptor.LAST_NAME, lastname);
    }

    public void age(final Integer age) {
        accessor.write(PersonDescriptor.AGE, age);
    }
}
