package de.tse.accessorconcept._03_read_and_write_interface.csv;

public class CsvFileDescriptor {

    public static CsvAttribute<String> FIRST_NAME = new CsvAttribute<>(String.class, 0);
    public static CsvAttribute<String> LAST_NAME = new CsvAttribute<>(String.class, 1);
    public static CsvAttribute<Integer> AGE = new CsvAttribute<>(Integer.class, 2);

    private CsvFileDescriptor() {

    }
}
