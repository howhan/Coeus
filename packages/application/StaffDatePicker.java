package application;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import data.Staff;

public class StaffDatePicker<S, T> extends TableCell<Staff, LocalDate> {
	  	private DatePicker datePicker;
	    private ObservableList<Staff> staffData;

	    /**
	     * Constructor.
	     *
	     * @param listBirthdays
	     */
	    public StaffDatePicker(ObservableList<Staff> listStaff) {

	        super();
	        
	        this.staffData = listStaff;
	        
	        if (datePicker == null) {
	        	createDatePicker();
	        }
	        setGraphic(datePicker);
	        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	        setEditable(true);
	        this.setOnMouseClicked(e -> OnCellClick(e));
	        Platform.runLater(new Runnable() {
	            @Override
	            public void run() {
	                datePicker.requestFocus();
	            }
	        });
	    }

	    private Object OnCellClick(MouseEvent e) {
			setEditable(this.getTableView().isEditable());
			return null;
		}

		/**
	     * Override TableCell updateItem method.
	     *
	     * @param item
	     * @param empty
	     */
	    @Override
	    public void updateItem(LocalDate item, boolean empty) {
	        super.updateItem(item, empty);
	        
	        //System.out.println("Row number " + this.getTableRow().getIndex() + " " + item + " is Editing " + isEditable() + " empty " + empty 
	        //		+ " tableview edit " + this.getTableView().isEditable());
	        
	        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	        if (null == this.datePicker) {
	            System.out.println("datePicker is NULL");
	        }
	        if (isEditing()) {
                setContentDisplay(ContentDisplay.TEXT_ONLY);
                if (null != item) {
                	//convert local date to text
                	setText ( item.format(fmt) );
                }
            	
            } else {
            	if (null != item) {
            		String text = item.format(fmt);
            		setText(text);
            		setGraphic(this.datePicker);
            		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            	}
            }
	    }


	    /**
	     * This method create a textfield with key and focus events.
	     */
	    private void createDatePicker() {
	        this.datePicker = new DatePicker();
	        datePicker.setPromptText("dd-MM-yyyy");
	        datePicker.setEditable(true);

	        datePicker.setOnAction(new EventHandler() {
	            public void handle(Event t) {
	                LocalDate date = datePicker.getValue();
	                int index = getIndex();
	                if (null != staffData) { 
	                	TableColumn<Staff, LocalDate> col = getTableColumn();
	                	if (col.getText() == StaffTable.DATEJOIN_COL) {
	                		staffData.get(index).setDateJoin(date);
	                	}
	                	if (col.getText() == StaffTable.DATELEFT_COL) {
	                		staffData.get(index).setDateLeft(date);
	                	}
	                }
	            }
	        });

	        setAlignment(Pos.CENTER);
	    }

	    /**
	     *
	     */
	    @Override
	    public void startEdit() {
	        super.startEdit();
	        System.out.println("Start Cell Edit!");
	        
	        
	    }

	    /**
	     * Override TableCell cancelEdit method.
	     */
	    @Override
	    public void cancelEdit() {
	        super.cancelEdit();
	        setContentDisplay(ContentDisplay.TEXT_ONLY);
	    }
	    
	    /**
	     * 
	     * @return 
	     */
	    public ObservableList<Staff> getStaffData() {
	        return staffData;
	    }

	    /**
	     * 
	     * @param staffData 
	     */
	    public void setStaffData(ObservableList<Staff> staffData) {
	        this.staffData = staffData;
	    }

	    /**
	     * 
	     * @return 
	     */
	    public DatePicker getDatePicker() {
	        return datePicker;
	    }

	    /**
	     * 
	     * @param datePicker 
	     */
	    public void setDatePicker(DatePicker datePicker) {
	        this.datePicker = datePicker;
	    }

}
