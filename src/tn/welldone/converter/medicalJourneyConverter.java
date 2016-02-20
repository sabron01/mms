package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.MedicalJourneyController;
import tn.welldone.model.MedicalJourney;


@FacesConverter("medicalJourneyConverter")
public class medicalJourneyConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof MedicalJourney) ? ((MedicalJourney) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		MedicalJourneyController data = context.getApplication()
				.evaluateExpressionGet(context, "#{MedicalJourneyController}",
						MedicalJourneyController.class);

		for (MedicalJourney medicalJourney : data.getList()) {

			if (medicalJourney.getId() == Integer.parseInt(value)) {
				return medicalJourney;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to MedicalJourney", value)));
	}

}
