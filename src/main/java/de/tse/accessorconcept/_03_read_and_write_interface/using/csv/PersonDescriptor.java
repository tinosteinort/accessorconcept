package de.tse.accessorconcept._03_read_and_write_interface.using.csv;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;

public class PersonDescriptor {

    public static CsvAttribute<String> FIRST_NAME = new CsvAttribute<>(String.class, 0);
    public static CsvAttribute<String> LAST_NAME = new CsvAttribute<>(String.class, 1);
    public static CsvAttribute<Integer> AGE = new CsvAttribute<>(Integer.class, 2);

    private PersonDescriptor() {

    }
}
