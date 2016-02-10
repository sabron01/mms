package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Notification.Type;


public class TypeConverter implements AttributeConverter<Type, String>{

	
	@Override
	public String convertToDatabaseColumn(Type attribute) {
        switch (attribute) {
        case Message:
            return "message";
        case Notification:
            return "notif";            
        case Alert:
            return "alert"; 
        case Task:
            return "task";             
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public Type convertToEntityAttribute(String dbData) {
        switch (dbData) {
        case "message":
            return Type.Message;
        case "notif":
            return Type.Notification;
        case "alert":
            return Type.Alert;      
        case "task":
            return Type.Task;             
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
