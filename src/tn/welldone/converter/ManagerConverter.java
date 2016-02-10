package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.EmployeeController;
import tn.welldone.model.Employee;


@FacesConverter("managerConverter")
public class ManagerConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Employee) ? ((Employee) value).getId() + ""
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

		for (Employee employee : data.getManagers()) {

			if (employee.getId() == Integer.parseInt(value)) {
				return employee;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Employee", value)));
	}

}
