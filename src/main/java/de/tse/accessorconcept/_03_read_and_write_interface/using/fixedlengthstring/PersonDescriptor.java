package de.tse.accessorconcept._03_read_and_write_interface.using.fixedlengthstring;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.fixedlengthstring.FixedLengthStringAttribute;

public class PersonDescriptor {

    public static final FixedLengthStringAttribute<String> FIRST_NAME = new FixedLengthStringAttribute<>(String.class, 0, 10);
    public static final FixedLengthStringAttribute<String> LAST_NAME = new FixedLengthStringAttribute<>(String.class, 10, 10);
    public static final FixedLengthStringAttribute<Integer> AGE = new FixedLengthStringAttribute<>(Integer.class, 20, 3);

    private PersonDescriptor() {

    }
}
