package de.tse.accessorconcept._03_read_and_write_interface;

import de.tse.accessorconcept.MyObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class ReadAndWriteInterface {

    @Test public void testImport() throws UnsupportedEncodingException {

        final byte[] dataFromFile = "Dagobert  Duck      100".getBytes("UTF-8");

        final MyObject myObject = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(100, myObject.getAge());
    }

    public MyObject doImport(final byte[] data) {

        final MyObject obj = new MyObject();

        final ByteArrayReader reader = new ByteArrayReader(data);

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

        final ByteArrayWriter writer = new ByteArrayWriter(data);

        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return data;
    }
}
