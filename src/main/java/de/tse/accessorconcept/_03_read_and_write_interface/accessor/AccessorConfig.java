package de.tse.accessorconcept._03_read_and_write_interface.accessor;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.reader.AttributeReader;
import de.tse.accessorconcept._03_read_and_write_interface.accessor.writer.AttributeWriter;

import java.util.Map;

public interface AccessorConfig<TUPEL_TYPE, ATTR_DESC_TYPE extends Attribute<?>> {

    <ATTR_TYPE> AttributeReader<TUPEL_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> readerFor(ATTR_DESC_TYPE attribute);

    <ATTR_TYPE> AttributeWriter<TUPEL_TYPE, ATTR_TYPE, ATTR_DESC_TYPE> writerFor(ATTR_DESC_TYPE attribute);

    Map<Class<?>, AttributeReader<TUPEL_TYPE, ?, ? extends Attribute<?>>> readers();

    Map<Class<?>, AttributeWriter<TUPEL_TYPE, ?, ? extends Attribute<?>>> writers();
}
