package de.tse.accessorconcept._03_read_and_write_interface.api.reader;

import de.tse.accessorconcept._03_read_and_write_interface.api.Attribute;

public interface AttributeReader<TUPEL_TYPE, ATTRIBUTE_TYPE, ATTRIBUTE_DESCRIPTION_TYPE extends Attribute<?>> {

    ATTRIBUTE_TYPE read(TUPEL_TYPE data, ATTRIBUTE_DESCRIPTION_TYPE attribute);
}
