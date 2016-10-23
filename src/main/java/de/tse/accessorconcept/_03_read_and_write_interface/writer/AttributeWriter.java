package de.tse.accessorconcept._03_read_and_write_interface.writer;

import de.tse.accessorconcept._03_read_and_write_interface.Attribute;

public interface AttributeWriter<T> {

    void write(byte[] data, Attribute<T> attribute, T value);
}
