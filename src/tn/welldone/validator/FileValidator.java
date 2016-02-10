package tn.welldone.validator;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator("fileValidator")
public class FileValidator implements Validator {

	@Override
	public void validate(FacesContext ctx, UIComponent comp, Object value)
			throws ValidatorException {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (file == null) {
			msgs.add(new FacesMessage("Please select a file"));
			throw new ValidatorException(msgs);
		}
		if (file.getSize() > 2048000) {
			msgs.add(new FacesMessage("file too big"));
		}
		if (!"application/pdf".equals(file.getContentType())) {
			msgs.add(new FacesMessage("not a pdf file"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
		
	}
	
}
