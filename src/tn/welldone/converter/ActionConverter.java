package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Tache.Action;

public class ActionConverter implements
		AttributeConverter<Action, String> {

	@Override
	public String convertToDatabaseColumn(Action attribute) {
		switch (attribute) {
		case CREATE:
			return "create";
		case UPDATE:
			return "update";
		case DELETE:
			return "delete";

		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public Action convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "create":
			return Action.CREATE;
		case "update":
			return Action.UPDATE;
		case "delete":
			return Action.DELETE;
		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}

}
