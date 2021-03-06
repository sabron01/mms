package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.PartnerAgencyController;
import tn.welldone.model.ServiceProvider;


@FacesConverter("clinicConverter")
public class ClinicConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof ServiceProvider) ? ((ServiceProvider) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		PartnerAgencyController data = context.getApplication()
				.evaluateExpressionGet(context, "#{PartnerAgencyController}",
						PartnerAgencyController.class);

		for (ServiceProvider agency : data.getClinics()) {

			if (agency.getId() == Integer.parseInt(value)) {
				return agency;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to ServiceProvider", value)));
	}

}
