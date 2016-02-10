package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.PatientController;
import tn.welldone.model.Patient;


@FacesConverter("patientConverter")
public class PatientConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Patient) ? ((Patient) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		PatientController data = context.getApplication()
				.evaluateExpressionGet(context, "#{PatientController}",
						PatientController.class);

		for (Patient patient : data.getPatients()) {

			if (patient.getId() == Integer.parseInt(value)) {
				return patient;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Patient", value)));
	}

}
