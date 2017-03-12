package de.tse.accessorconcept._03_read_and_write_interface.accessor;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;

import java.util.Map;

public interface AccessorConfig<TUPEL_TYPE> {

    <T> AttributeReader<TUPEL_TYPE, T, ? extends Attribute<T>> readerFor(Class<T> cls);

    <T> AttributeWriter<TUPEL_TYPE, T, ? extends Attribute<T>> writerFor(Class<T> cls);

    Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readers();

    Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writers();
}
