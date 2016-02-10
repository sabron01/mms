package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.DataParameterController;
import tn.welldone.model.ProviderType;


@FacesConverter("providerTypeConverter")
public class ProviderTypeConverter implements Converter {
	
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof ProviderType) ? ((ProviderType) value).getId() + ""
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

		for (ProviderType type : data.getProviderTypes()) {

			if (type.getId() == Integer.parseInt(value)) {
				return type;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to ProviderType", value)));
	}

}
