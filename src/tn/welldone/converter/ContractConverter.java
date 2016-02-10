package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.ContractController;
import tn.welldone.model.Contract;


@FacesConverter("contractConverter")
public class ContractConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Contract) ? ((Contract) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		ContractController data = context.getApplication()
				.evaluateExpressionGet(context, "#{ContractController}",
						ContractController.class);

		for (Contract contract : data.getList()) {

			if (contract.getId() == Integer.parseInt(value)) {
				return contract;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Contract", value)));
	}

}
