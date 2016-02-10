package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.DataParameterController;
import tn.welldone.model.Country;


@FacesConverter("countryConverter")
public class CountryConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Country) ? ((Country) value).getId() + ""
				: null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		DataParameterController data = context.getApplication()
				.evaluateExpressionGet(context, "#{DataParameterController}",
						DataParameterController.class);

		for (Country country : data.getCountries()) {

			if (country.getId() == Integer.parseInt(value)) {
				return country;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Country", value)));
	}

}
