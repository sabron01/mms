package tn.welldone.converter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	   final public static String DATE_FORMAT = "dd-MMM-yyyy";
	   final public static String TIME_FORMAT = "HH:mm";
	   /**
	    * Display a calendar as a string and it can be 'today', 'yesterday' or 'tomorrow'.
	    * Note that this implentation doesn't handle today/tomorrow across the new year.
	    *
	    * @param showTime toggles whether the time is displayed as well
	    */
	   public static String formatRelativeDate(final Date date, final boolean showTime) {
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(date);

	      Calendar now = Calendar.getInstance();
	      final int today = now.get(Calendar.DAY_OF_YEAR);
	      final SimpleDateFormat timeFormat = new SimpleDateFormat(" " + TIME_FORMAT);
	      final String time = showTime ? timeFormat.format(calendar.getTime()) : "";

	      if (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
	         if (today == calendar.get(Calendar.DAY_OF_YEAR)) {
	            return "today" + time;
	         }
	         else if (today - 1 == calendar.get(Calendar.DAY_OF_YEAR)) {
	            return "yesterday" + time;
	         }
	         else if (today + 1 == calendar.get(Calendar.DAY_OF_YEAR)) {
	            return "tomorrow" + time;
	         }
	      }
	      final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	      return dateFormat.format(date) + time;
	   }
	}
