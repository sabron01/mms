package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.DepartmentController;
import tn.welldone.model.Department;


@FacesConverter("departmentConverter")
public class DepartmentConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Department) ? ((Department) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {

		if (value == null) {
			return null;
		}

		DepartmentController data = context.getApplication()
				.evaluateExpressionGet(context, "#{DepartmentController}",
						DepartmentController.class);

		for (Department type : data.getList()) {

			if (type.getId() == Integer.parseInt(value)) {
				return type;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Department", value)));
	}

}
