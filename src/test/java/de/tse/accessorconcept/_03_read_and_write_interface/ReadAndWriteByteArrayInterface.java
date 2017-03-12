package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept.MyObject;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfig;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.AccessorConfigBuilder;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayAttribute;
import de.tse.accessorconcept._03_read_and_write_interface.using.bytearray.PersonByteArrayReader;
import de.tse.accessorconcept._03_read_and_write_interface.using.bytearray.PersonByteArrayWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.reader.IntegerAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.reader.StringAttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.writer.IntegerAttributeWriter;
import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.writer.StringAttributeWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ReadAndWriteByteArrayInterface {

    private final AccessorConfig<byte[]> byteArrayConfig = new AccessorConfigBuilder<byte[], ByteArrayAttribute<?>>()
            .registerReader(String.class, new StringAttributeReader())
            .registerReader(Integer.class, new IntegerAttributeReader())
            .registerWriter(String.class, new StringAttributeWriter())
            .registerWriter(Integer.class, new IntegerAttributeWriter())
            .build();

    @Test public void testImport() throws UnsupportedEncodingException {

        final byte[] dataFromFile = "Dagobert  Duck      100".getBytes("UTF-8");

        final MyObject myObject = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(100, myObject.getAge());
    }

    public MyObject doImport(final byte[] data) {

        final MyObject obj = new MyObject();

        final PersonByteArrayReader reader = new PersonByteArrayReader(byteArrayConfig, data);

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

        Assert.assertArrayEquals(
                "Dagobert  Duck      100".getBytes("UTF-8"),
                doExport(obj));
    }

    public byte[] doExport(final MyObject obj) {

        final byte[] data = new byte[23];

        final PersonByteArrayWriter writer = new PersonByteArrayWriter(byteArrayConfig, data);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return data;
    }
}
