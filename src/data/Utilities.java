
package data;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
}