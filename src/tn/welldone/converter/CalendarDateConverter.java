package tn.welldone.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("calendarDateConverter")
public class CalendarDateConverter implements Converter {

	

	@Override
	public String getAsString(FacesContext context, UIComponent c, Object object)
			throws ConverterException {

		Calendar calendar = (Calendar) object;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(calendar.getTime());
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		if (arg2.isEmpty()) {
			throw new ConverterException(new FacesMessage("Please instert a date."));
		}
		try {
			date = sdf.parse(arg2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (date != null)
			calendar.setTime(date);
		return calendar;
	}

}