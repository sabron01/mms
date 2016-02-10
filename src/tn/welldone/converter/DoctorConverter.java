package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.DoctorController;
import tn.welldone.model.Doctor;


@FacesConverter("doctorConverter")
public class DoctorConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Doctor) ? ((Doctor) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		DoctorController data = context.getApplication()
				.evaluateExpressionGet(context, "#{DoctorController}",
						DoctorController.class);

		for (Doctor contract : data.getList()) {

			if (contract.getId() == Integer.parseInt(value)) {
				return contract;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Doctor", value)));
	}

}
