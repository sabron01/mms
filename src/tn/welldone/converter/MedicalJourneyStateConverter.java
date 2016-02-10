package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.MedicalJourney.MedicalJourneyState;

public class MedicalJourneyStateConverter implements
		AttributeConverter<MedicalJourneyState, String> {

	@Override
	public String convertToDatabaseColumn(MedicalJourneyState attribute) {
		switch (attribute) {
		case PLANNED:
			return "planned";
		case ACTIVE:
			return "active";
		case SUSPENDED:
			return "suspended";
		case ACHIEVED:
			return "achieved";
		case CANCELED:
			return "canceled";
		case REJECTED:
			return "rejected";
		case DELETED:
			return "deleted";
		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public MedicalJourneyState convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "planned":
			return MedicalJourneyState.PLANNED;
		case "active":
			return MedicalJourneyState.ACTIVE;
		case "suspended":
			return MedicalJourneyState.SUSPENDED;
		case "achieved":
			return MedicalJourneyState.ACHIEVED;
		case "canceled":
			return MedicalJourneyState.CANCELED;
		case "rejected":
			return MedicalJourneyState.REJECTED;
		case "deleted":
			return MedicalJourneyState.DELETED;
		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}

}
