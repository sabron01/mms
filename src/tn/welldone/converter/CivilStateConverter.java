package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Person.CivilState;

public class CivilStateConverter implements AttributeConverter<CivilState, String>{

	@Override
	public String convertToDatabaseColumn(CivilState attribute) {
        switch (attribute) {
        case Married:
            return "M";
        case Celibataire:
            return "C";
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public CivilState convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "M":
            return CivilState.Married;
        case "C":
            return CivilState.Celibataire;
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
