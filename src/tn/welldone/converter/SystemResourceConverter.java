package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.SystemResourceController;
import tn.welldone.model.SystemResource;

@FacesConverter("systemResourceConverter")
public class SystemResourceConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		SystemResourceController data = context.getApplication().evaluateExpressionGet(context,
				"#{SystemResourceController}", SystemResourceController.class);
		
		for (SystemResource systemResource : data.getList()) {

			if (systemResource.getId() == Integer.parseInt(value)) {
				return systemResource;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to SystemResource", value)));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof SystemResource) ? ((SystemResource) value).getId() + "" : null;
	}

}
