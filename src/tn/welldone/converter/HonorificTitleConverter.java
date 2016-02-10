package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Person.HonorificTitle;

public class HonorificTitleConverter implements AttributeConverter<HonorificTitle, String>{

	@Override
	public String convertToDatabaseColumn(HonorificTitle attribute) {
        switch (attribute) {
        case Mr:
            return "MR";
        case Mrs:
            return "MRS";
        case Miss:
            return "MISS";
        case Ms:
            return "MS";            
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public HonorificTitle convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "MR":
            return HonorificTitle.Mr;
        case "MRS":
            return HonorificTitle.Mrs;
        case "MISS":
            return HonorificTitle.Miss;
        case "MS":
            return HonorificTitle.Ms;            
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
