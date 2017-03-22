package com.qiita.nimzo6689.etude.jpa.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * BooleanToStringConverter
 *
 * @author nimzo6689
 */
@Converter(autoApply = true)
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return attribute ? "1" : "0";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return dbData == null ? null : "0".equals(dbData);
    }
}
