package data;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import application.HoursTable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HoursCC {
	public DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private SimpleStringProperty sendingCostCenter;
	private ObjectProperty<LocalDate> dateLogged;
	private ObjectProperty<LocalDate> dateSubmitted;
	private SimpleStringProperty employeeId;
	private SimpleStringProperty fullName;
	private SimpleStringProperty subProjectId;
	private SimpleStringProperty subProjectName;
	private SimpleStringProperty projectGroup;
	private SimpleStringProperty salesType;
	private SimpleStringProperty receivingCostCenter;
	private SimpleDoubleProperty hours;
	
	public HoursCC() {}
	
    public HoursCC(String input) {
    	//Expecting a string input with 
		List<String> fields = (List<String>) Arrays.asList(input.toString().split(";"));
		for (int i=0; i<fields.size(); i++) {
			String[] field = fields.get(i).split(":");
			if (field.length == 2) {
				switch (field[0]) {
					case HoursTable.SENDINGCOSTCENTER_COL:
						this.sendingCostCenter = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.DATELOGGED_COL:
						this.dateLogged = new SimpleObjectProperty<LocalDate>(getLocalDate(field[1]));
						break;
					case HoursTable.DATESUBMITTED_COL:
						this.dateSubmitted = new SimpleObjectProperty<LocalDate>(getLocalDate(field[1]));
						break;
					case HoursTable.EMPLOYEEID_COL:
						this.employeeId = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.FULLNAME_COL:
						this.fullName = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.SUBPROJECTID_COL:
						this.subProjectId = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.SUBPROJECTNAME_COL:
						this.subProjectName = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.PROJECTGROUP_COL:
						this.projectGroup = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.SALESTYPE_COL:
						this.salesType = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.RECEIVINGCOSTCENTER_COL:
						this.receivingCostCenter = new SimpleStringProperty(field[1]);
						break;
					case HoursTable.HOURS_COL:
						this.hours = new SimpleDoubleProperty((double)Float.parseFloat(field[1]));
						break;
				}
			}
		}
    }
	

    public LocalDate getLocalDate (String date) {
    	return LocalDate.parse(date, this.dateFormat);
    }
      
	public Double getHours() {
		return hours.get();
	}
	public void setHours(Double in) {
		hours.set(in);
	}
	public DoubleProperty hoursProperty() {
		return hours;
	}
    public String getSendingCostCenter() {
        return sendingCostCenter.get();
    }
    public void setSendingCostCenter (String in) {
    	sendingCostCenter.set(in);
    }
    public StringProperty SendingCostCenterProperty() {
    	return sendingCostCenter;
    }

    public String getEmployeeId() {
        return employeeId.get();
    }
    public void setEmployeeId (String in) {
    	System.out.println("in Size = " + in.length());
    	employeeId.set(in);
    }
    public StringProperty employeeIDProperty() {
    	return employeeId;
    }
    
    public String getProjectGroup() {
        return projectGroup.get();
    }
    public void setProjectGroup (String in) {
    	projectGroup.set(in);
    }
    public StringProperty projectGroupProperty() {
    	return projectGroup;
    }
    
    public String getFullName() {
        return fullName.get();
    }
    public void setFullName (String in) {
    	fullName.set(in);
    }
    public StringProperty fullNameProperty() {
    	return fullName;
    }
    public String getSubProjectId() {
        return subProjectId.get();
    }
    public void setSubProjectId (String in) {
    	subProjectId.set(in);
    }
    public StringProperty subProjectIdProperty() {
    	return subProjectId;
    }
    public String getSubProjectName() {
        return subProjectName.get();
    }
    public void setSubProjectName (String in) {
    	subProjectName.set(in);
    }
    public StringProperty subProjectNameProperty() {
    	return subProjectName;
    }
    public String getSalesType() {
        return salesType.get();
    }
    public void setSalesType (String in) {
    	salesType.set(in);
    }
    public StringProperty salesTypeProperty() {
    	return salesType;
    }
    public String getReceivingCostCenter() {
    	return receivingCostCenter.get();
    }
    public void setReceivingCostCenter(String in) {
    	receivingCostCenter.set(in);
    }
    public StringProperty receivingCostCenterProperty() {
    	return receivingCostCenter;
    }
    public LocalDate getDateLogged() {
        return dateLogged.get();
    }
    public void setDateLogged(LocalDate in) {
        this.dateLogged.set(in);
    }
    public ObjectProperty<LocalDate> dateLoggedProperty() {
        return dateLogged;
    }
    public LocalDate getDateSubmitted() {
        return dateSubmitted.get();
    }
    public void setDateSubmitted(LocalDate in) {
        this.dateSubmitted.set(in);
    }
    public ObjectProperty<LocalDate> dateSubmittedProperty() {
        return dateSubmitted;
    }
}
