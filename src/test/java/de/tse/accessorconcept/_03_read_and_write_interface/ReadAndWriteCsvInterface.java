package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept.MyObject;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfigBuilder;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.CsvData;
import de.tse.accessorconcept._03_read_and_write_interface.using.csv.PersonCsvReader;
import de.tse.accessorconcept._03_read_and_write_interface.using.csv.PersonCsvWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.reader.CsvIntegerAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.reader.CsvStringAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.writer.CsvIntegerAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.csv.writer.CsvStringAttributeWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ReadAndWriteCsvInterface {

    private final AccessorConfig<CsvData> csvConfig = new AccessorConfigBuilder<CsvData, CsvAttribute<?>>()
            .registerReader(String.class, new CsvStringAttributeReader())
            .registerReader(Integer.class, new CsvIntegerAttributeReader())
            .registerWriter(String.class, new CsvStringAttributeWriter())
            .registerWriter(Integer.class, new CsvIntegerAttributeWriter())
            .build();

    @Test public void testImport() throws UnsupportedEncodingException {

        final CsvData data = new CsvData("Dagobert", "Duck", "100");

        final MyObject myObject = doImport(data);

        Assert.assertEquals("Dagobert", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(100, myObject.getAge());
    }

    public MyObject doImport(final CsvData row) {

        final MyObject obj = new MyObject();

        final PersonCsvReader reader = new PersonCsvReader(csvConfig, row);

        reader.firstname().ifPresent(obj::setFirstname);
        reader.lastname().ifPresent(obj::setLastname);
        reader.age().ifPresent(obj::setAge);

        return obj;
    }

    @Test public void testExport() throws UnsupportedEncodingException {

        final MyObject obj = new MyObject();
        obj.setFirstname("Dagobert");
        obj.setLastname("Duck");
        obj.setAge(100);

        Assert.assertEquals(
                new CsvData("Dagobert", "Duck", "100"),
                doCsvExport(obj));
    }

    public CsvData doCsvExport(final MyObject obj) {

        final CsvData row = new CsvData(3);

        final PersonCsvWriter writer = new PersonCsvWriter(csvConfig, row);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return row;
    }
}
