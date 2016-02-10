package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.DataParameterController;
import tn.welldone.model.Region;

@FacesConverter("regionConverter")
public class regionConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Region) ? ((Region) value).getId() + "" : null;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		DataParameterController data = context.getApplication().evaluateExpressionGet(context,
				"#{DataParameterController}", DataParameterController.class);
		
		for (Region region : data.getRegions()) {
			if (region.getId() == Integer.parseInt(value)) {
				return region;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Region", value)));
	}


}
