package de.tse.accessorconcept._03_read_and_write_interface.api;

import de.tse.accessorconcept._03_read_and_write_interface.api.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.api.writer.AttributeWriter;

public interface AccessorConfig<TUPEL_TYPE> {

    <T> AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> readerFor(Class<T> cls);

    <T> AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writerFor(Class<T> cls);
}
