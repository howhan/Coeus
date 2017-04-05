package statistics;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import data.HoursCC;
import files.Xsl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HoursManager {

	private ArrayList<HoursCC> mData;
	public HoursManager() {
		
	}
	
	public ObservableList<HoursCC> LoadFile (String filename, ArrayList<String> cols) throws IOException {
		Xsl excel = new Xsl (filename);
		if (!excel.VerifyTableHeader(1, cols)) {
			System.out.print("This is bad!");
		}
				
		ArrayList<String> input = excel.ReadFileToTable(cols);
		if (input == null) {
			return null;
		}
		
		mData = new ArrayList<HoursCC>();
		for (int i=0; i<input.size(); i++) {
			HoursCC hours = new HoursCC(input.get(i));
			mData.add(hours);
		}		
		
		ObservableList<HoursCC> observableData = FXCollections.observableArrayList(mData);
		return observableData;	
	}
	
	public double QueryTotalHours (String id, int month, int year) {
		double results = 0.0;
		for (HoursCC row : mData ) {
			if (row.getEmployeeId().equals(id)) {
				results += row.getHours();
			}
		}
		return results;
	}
	
	  public double SumHoursByMonth (String employeeId, Month month, String projectGroup) {
	    	double hours = 0.0;
	    	int match = 0;
	    	for (HoursCC row : mData) {
	    		//find the row matching employee id
	    		if (row.getEmployeeId().equals(employeeId)) {
	    			// find the month matching row
	    			if (row.getDateLogged().getMonth() == month) {
	    				// find the matching projectGroup
	    				if (row.getProjectGroup().equals(projectGroup)) {
	    						hours += row.getHours();
	    				}
	    			}
	    		}
	    	}
	    	return hours;
	    }
	       
	    public double SumHoursByDay (String employeeId, LocalDate date, String projectGroup) {
	    	double hours = 0.0;
	    	for (HoursCC row : mData) {
	    		if (row.getEmployeeId().equals(employeeId)) {
	    			// find the month matching row
	    			if (row.getDateLogged() == date) {
	    				// find the matching projectGroup
	    				if (row.getProjectGroup().equals(projectGroup)) {
	    						hours += row.getHours();
	    				}
	    				
	    			}
	    		}
	    	}
	    	return hours;
	    }
}
