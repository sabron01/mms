package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Person.Gender;

public class GenderConverter implements AttributeConverter<Gender, String>{

	@Override
	public String convertToDatabaseColumn(Gender attribute) {
        switch (attribute) {
        case MALE:
            return "H";
        case FEMALE:
            return "F";
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "H":
            return Gender.MALE;
        case "F":
            return Gender.FEMALE;
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
