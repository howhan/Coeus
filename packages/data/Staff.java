package data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import application.StaffTable;


public class Staff {
 	 	public static double version = 1.0;
 	 	public static String defaultJoinDate = "01.01.2015";
        private  SimpleStringProperty employeeId;
        private  SimpleStringProperty fullName;
        private  SimpleStringProperty costCenter;
        private  SimpleStringProperty emailAddress;    	
        private  ObjectProperty<LocalDate> dateJoin;
        private  ObjectProperty<LocalDate> dateLeft;
        
    	public Staff() {
            this.employeeId = null;
            this.fullName = null;
            this.costCenter = null;
            this.emailAddress = null;
            this.dateJoin = null;
            this.dateLeft = null;	
    	}
    	
        public Staff(String employeeId, String fullName, String costCenter, String emailAddress, LocalDate in, LocalDate out) {
            this.employeeId = new SimpleStringProperty (employeeId);
            this.fullName = new SimpleStringProperty (fullName);
            this.costCenter = new SimpleStringProperty (costCenter);
            this.emailAddress = new SimpleStringProperty (emailAddress);
            if (in == null) {
            	this.dateJoin = new SimpleObjectProperty<LocalDate> (getLocalDate(defaultJoinDate));
            }
            else {
            	this.dateJoin = new SimpleObjectProperty<LocalDate> (in);
            }
            
            this.dateLeft = new SimpleObjectProperty<LocalDate> (out);
        }
        
        public Staff(String input) {
        	//Expecting a string input with 
    		List<String> fields = (List<String>) Arrays.asList(input.toString().split(";"));
    		
    		this.dateJoin = new SimpleObjectProperty<LocalDate>(getLocalDate(defaultJoinDate));
    		this.dateLeft = new SimpleObjectProperty<LocalDate>();
    		
    		for (int i=0; i<fields.size(); i++) {
    			String[] field = fields.get(i).split(":");
    			switch (field[0]) {
    				case StaffTable.STAFFID_COL:
    					this.employeeId = new SimpleStringProperty(field[1]);
    					break;
    				case StaffTable.STAFFNAME_COL:
    					this.fullName = new SimpleStringProperty(field[1]);
    					break;
    				case StaffTable.STAFFCOSTCENTER_COL:	
    					this.costCenter= new SimpleStringProperty(field[1]);
    					break;
    				case StaffTable.STAFFEMAIL_COL:	
    					this.emailAddress= new SimpleStringProperty(field[1]);
    					break;	
    				case StaffTable.DATEJOIN_COL:
    					setDateJoin ( getLocalDate(field[1]) );
    					break;
    				case StaffTable.DATELEFT_COL:
    					setDateLeft ( getLocalDate(field[1]) );
    					break;	
    				default:
    					break;
    			}
    		}
        }
 
        public String getEmployeeId() {
            return employeeId.get();
        }
 
        public void setEmployeeId (String id) {
        	employeeId.set(id);
        }
        
        public StringProperty employeeIDProperty() {
        	return employeeId;
        }
        
        public String getFullName() {
            return fullName.get();
        }
 
        public void setFullName (String fName) {
        	fullName.set(fName);
        }
 
        public StringProperty fullNameProperty() {
        	return fullName;
        }
        
        public String getCostCenter() {
            return costCenter.get();
        }
 
        public void setCostCenter(String cCenter) {
        	costCenter.set(cCenter);
        }
        
        public StringProperty costCenterProperty() {
        	return costCenter;
        }
        
        public String getEmailAddress() {
        	return emailAddress.get();
        }
        
        public void setEmailAddress(String email) {
        	emailAddress.set(email);
        }
        
        public StringProperty emailAddressProperty() {
        	return emailAddress;
        }
        
        public LocalDate getDateJoin() {
            return dateJoin.get();
        }

        public void setDateJoin(LocalDate in) {
            this.dateJoin.set(in);
        }
        
        public ObjectProperty<LocalDate> dateJoinProperty() {
            return dateJoin;
        }
                
        public LocalDate getDateLeft() {
        	return dateLeft.get();
        }
        
        private String getDateString(LocalDate date) {
        	if (date == null) {
        		return null;
        	}
        	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        	String text = date.format(fmt);
        	return text;
        }
        
        private LocalDate getLocalDate (String date) {
        	return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        
        public void setDateLeft(LocalDate out) {
        	dateLeft.set(out);
        }
        public ObjectProperty<LocalDate> dateLeftProperty() {
            return dateLeft;
        }
        
        @Override
        public String toString() {
        	String res = getEmployeeId() + ";" + getFullName() + ";" + getCostCenter() + ";"
        				+ getEmailAddress() + ";" + getDateString(getDateJoin()) + ";"
        				+ getDateString(getDateLeft());
        	
        	return res;
        }
}
