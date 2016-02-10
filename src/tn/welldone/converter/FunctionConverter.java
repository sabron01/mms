package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.EmployeeController;
import tn.welldone.model.Function;


@FacesConverter("functionConverter")
public class FunctionConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Function) ? ((Function) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		EmployeeController data = context.getApplication()
				.evaluateExpressionGet(context, "#{EmployeeController}",
						EmployeeController.class);

		for (Function function : data.getFunctions()) {

			if (function.getId() == Integer.parseInt(value)) {
				return function;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Function", value)));
	}

}
