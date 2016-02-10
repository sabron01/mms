package tn.welldone.converter;

import javax.persistence.AttributeConverter;

import tn.welldone.model.Contract.ContractState;

public class ContractStateConverter  implements AttributeConverter<ContractState, String>{

	@Override
	public String convertToDatabaseColumn(ContractState attribute) {
        switch (attribute) {
        case ACTIVE:
            return "A";
        case SUSPENDED:
            return "S";
        case CANCELED:
            return "C";
        case REJECTED:
            return "R";
        case DELETED:
            return "D";         
        default:
            throw new IllegalArgumentException("Unknown" + attribute);
        }
	}

	@Override
	public ContractState convertToEntityAttribute(String dbData) {
        switch (dbData) {
        
        case "A":
            return ContractState.ACTIVE;
        case "S":
            return ContractState.SUSPENDED;
        case "C":
            return ContractState.CANCELED;
        case "R":
            return ContractState.REJECTED;
        case "D":
            return ContractState.DELETED;
        default:
            throw new IllegalArgumentException("Unknown" + dbData);
    }
	}

}
