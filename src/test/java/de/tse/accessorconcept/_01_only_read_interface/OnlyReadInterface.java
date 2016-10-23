package de.tse.accessorconcept._01_only_read_interface;

import de.tse.accessorconcept.MyObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class OnlyReadInterface {

    @Test public void testRead() throws UnsupportedEncodingException {

        final byte[] dataFromFile = "Dagobert  Duck      100".getBytes("UTF-8");

        final MyObject myObject = doImport(dataFromFile);

        Assert.assertEquals("Dagobert", myObject.getFirstname());
        Assert.assertEquals("Duck", myObject.getLastname());
        Assert.assertEquals(100, myObject.getAge());
    }

    public MyObject doImport(final byte[] data) {

        final MyObject obj = new MyObject();

        final OnlyReadByteArrayReader reader = new OnlyReadByteArrayReader(data);
        reader.firstname().ifPresent(obj::setFirstname);
        reader.lastname().ifPresent(obj::setLastname);
        reader.age().ifPresent(obj::setAge);

        return obj;
    }
}
