package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.GroupeController;
import tn.welldone.model.Groupe;

@FacesConverter("groupeConverter")
public class GroupeConverter implements Converter {

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Groupe) ? ((Groupe) value).getId() + "" : null;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		GroupeController data = context.getApplication().evaluateExpressionGet(
				context, "#{GroupeController}", GroupeController.class);

		for (Groupe groupe : data.getList()) {

			if (groupe.getId() == Integer.parseInt(value)) {
				return groupe;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Groupe", value)));
	}

}
