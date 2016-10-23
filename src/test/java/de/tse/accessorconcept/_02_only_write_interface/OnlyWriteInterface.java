package de.tse.accessorconcept._02_only_write_interface;

import de.tse.accessorconcept.MyObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class OnlyWriteInterface {

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

        final OnlyWriteByteArrayWriter writer = new OnlyWriteByteArrayWriter(data);
        writer.firstname(obj.getFirstname());
        writer.lastname(obj.getLastname());
        writer.age(obj.getAge());

        return data;
    }
}
