package de.tse.accessorconcept._00_dont_do_it_this_way;

import de.tse.accessorconcept._03_read_and_write_interface.interfaces.bytearray.ByteArrayHelper;
import de.tse.accessorconcept.MyObject;

/**
 * Created by Tino on 05.02.2017.
 */
public class LegacyImport {

    public MyObject doImport(final byte[] data) {

        final MyObject obj = new MyObject();

        // Die Zahlen entsprechen der Position und Länge der zu lesenden Daten
        obj.setFirstname(ByteArrayHelper.readString(data, 0, 10));
        obj.setLastname(ByteArrayHelper.readString(data, 10, 10));
        obj.setAge(ByteArrayHelper.readInteger(data, 20, 3));
        // ...

        return obj;
    }
}
