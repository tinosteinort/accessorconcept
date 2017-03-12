package de.tse.accessorconcept._03_read_and_write_interface.accessor.writer;

import de.tse.accessorconcept._03_read_and_write_interface.accessor.Attribute;

public interface AttributeWriter<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    void write(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute, ATTRIBUTE_TYPE value);
}
