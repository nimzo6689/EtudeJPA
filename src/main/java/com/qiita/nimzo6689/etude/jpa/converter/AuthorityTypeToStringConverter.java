package com.qiita.nimzo6689.etude.jpa.converter;

import com.qiita.nimzo6689.etude.jpa.code.AuthorityType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * AuthorityTypeToStringConverter
 *
 * @author nimzo6689
 */
@Converter(autoApply = true)
public class AuthorityTypeToStringConverter implements AttributeConverter<AuthorityType, String> {

    @Override
    public String convertToDatabaseColumn(AuthorityType attribute) {
        return attribute.getValue();
    }

    @Override
    public AuthorityType convertToEntityAttribute(String dbData) {
        return AuthorityType.of(dbData);
    }

}
