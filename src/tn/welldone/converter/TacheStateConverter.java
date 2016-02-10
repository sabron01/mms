package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Tache.TacheState;

public class TacheStateConverter implements
		AttributeConverter<TacheState, String> {

	@Override
	public String convertToDatabaseColumn(TacheState attribute) {
		switch (attribute) {
		case CREATED:
			return "created";
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
	public TacheState convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "created":
			return TacheState.CREATED;
		case "planned":
			return TacheState.PLANNED;
		case "active":
			return TacheState.ACTIVE;
		case "suspended":
			return TacheState.SUSPENDED;
		case "achieved":
			return TacheState.ACHIEVED;
		case "canceled":
			return TacheState.CANCELED;
		case "rejected":
			return TacheState.REJECTED;
		case "deleted":
			return TacheState.DELETED;
		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}

}
