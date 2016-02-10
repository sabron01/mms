package tn.welldone.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import tn.welldone.controller.PermissionController;
import tn.welldone.model.Permission;

@FacesConverter("permissionConverter")
public class PermissionConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}

		PermissionController data = context.getApplication()
				.evaluateExpressionGet(context, "#{PermissionController}",
						PermissionController.class);

		for (Permission permission : data.getPermissions()) {

			if (permission.getId() == Integer.parseInt(value)) {
				return permission;
			}
		}

		throw new ConverterException(new FacesMessage(String.format(
				"Cannot convert %s to Permission", value)));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (value instanceof Permission) ? ((Permission) value).getId() + ""
				: null;
	}

}
