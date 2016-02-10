package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.PhoneNumber.PhoneType;

public class PhoneTypeConverter implements AttributeConverter<PhoneType, String>{

	@Override
	public String convertToDatabaseColumn(PhoneType attribute) {
        switch (attribute) {
        case MOBILE:
            return "M";
        case FIXE:
            return "F";
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public PhoneType convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "M":
            return PhoneType.MOBILE;
        case "F":
            return PhoneType.FIXE;
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
