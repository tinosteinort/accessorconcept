package de.tse.accessorconcept._03_read_and_write_interface.reader;

import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public interface AttributeReader<T> {

    T read(byte[] data, Attribute<T> attribute);
}
