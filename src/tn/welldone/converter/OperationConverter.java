package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Permission.Operation;


public class OperationConverter implements AttributeConverter<Operation, String>{


	@Override
	public String convertToDatabaseColumn(Operation attribute) {
        switch (attribute) {
        case VIEW:
            return "view";
        case ADD:
            return "add";            
        case EDIT:
            return "edit";
        case UPDATE:
            return "update";
        case DELETE:
            return "delete";      
        case PRINT:
            return "print";            
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public Operation convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "view":
            return Operation.VIEW;
        case "add":
            return Operation.ADD;
        case "edit":
            return Operation.EDIT;            
        case "update":
            return Operation.UPDATE;
        case "delete":
            return Operation.DELETE;
        case "print":
            return Operation.PRINT;            
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
