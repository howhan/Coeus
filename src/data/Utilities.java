
package data;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//singleton class
public class Utilities {
	private static Utilities utilities = new Utilities();
	
	public static Utilities Instance() {
		return utilities;
	}
	
	//default constructor
	public Utilities() {}

	public String LocalDateToString(LocalDate date, DateTimeFormatter dateFormat) {
    	if (date == null) {
    		return null;
    	}
    	String text = date.format(dateFormat);
    	return text;	
	}
    
    public LocalDate StringToLocalDate (String date, DateTimeFormatter dateFormat) {
    	return LocalDate.parse(date, dateFormat);
    }
    
	public String MonthNumberToString(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	public int StringToMonthNumber(String month) {
		Date date;
		try {
			date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
}