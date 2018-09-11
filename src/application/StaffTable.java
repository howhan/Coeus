package application;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import application.DataScape;
import data.Staff;
import files.Xml;


public class StaffTable extends DataScape {
	private static final String LOADFILTER = "LOAD FILTER";
	private static final String SAVE = "SAVE";
	private static final String EDIT = "EDIT";
	private static final String ADD = "ADD";
	private static final String DELETE = "DELETE";
	private static final String CANCEL = "CANCEL";
	public static final String STAFFID_COL = "Employee ID";
	public static final String STAFFCOSTCENTER_COL = "Cost Center";
	public static final String STAFFNAME_COL = "Full Name";
	public static final String STAFFEMAIL_COL = "Email Address";
	public static final String DATEJOIN_COL = "Date Join";
	public static final String DATELEFT_COL = "Date Left";
	
	/***********************************************************************************************
	 * The Staff Table has a Label, a Table and a HBox of Buttons, all vertically stored a VBox
	 * *********************************************************************************************
	 */
	//UI Contents
	private VBox mTableBox;
	private TableView <Staff> mTable;
	private Button mMultiButton;
	private Button mAddRowButton;
	private Button mDeleteRowButton;
	private Button mCancelButton;
	private Label mTableLabel;
	private Button mLoadFile;
	private ArrayList<String> mCostCenters;
	
	//Utilities
	
	//Data
	private ObservableList<Staff> data = FXCollections.observableArrayList();
	private ObservableList <TableColumn<Staff,String>> stringColumns = FXCollections.observableArrayList();
	private ObservableList <TableColumn<Staff,LocalDate>> dateColumns = FXCollections.observableArrayList();
	
	public StaffTable(Tableau plug) {
		super(plug);
		mTableBox = new VBox();
	}
	
	@Override
	public boolean Initialize() {
		mTable = new TableView<Staff>();
		mCostCenters = new ArrayList<String>();

		TableColumn<Staff, String> idCol = new TableColumn<Staff,String>(STAFFID_COL);		
	    TableColumn<Staff, String> nameCol = new TableColumn<Staff, String>(STAFFNAME_COL);
	    TableColumn<Staff, String> costCenterCol = new TableColumn<Staff, String>(STAFFCOSTCENTER_COL);
	    TableColumn<Staff, String> emailCol = new TableColumn<Staff, String>(STAFFEMAIL_COL);
	    stringColumns.addAll(idCol, nameCol, costCenterCol, emailCol);
	    
	    TableColumn<Staff, LocalDate> dateInCol = new TableColumn<Staff, LocalDate>(DATEJOIN_COL);
	    TableColumn<Staff, LocalDate> dateOutCol = new TableColumn<Staff, LocalDate>(DATELEFT_COL);
	    dateColumns.addAll(dateInCol, dateOutCol);
	    
	     
	    //Size of Columns *** HARDCODED FOR NOW****
	    idCol.prefWidthProperty().bind(mTable.widthProperty().divide(12));				//16%
	    nameCol.prefWidthProperty().bind(mTable.widthProperty().divide(3));				//33%
	    costCenterCol.prefWidthProperty().bind(mTable.widthProperty().divide(12));		//16%
	    emailCol.prefWidthProperty().bind(mTable.widthProperty().divide(4));		//25%
	    dateInCol.prefWidthProperty().bind(mTable.widthProperty().divide(8));		//33%
	    dateOutCol.prefWidthProperty().bind(mTable.widthProperty().divide(8));		//33%
	    
	    //Cell Value Factory. The input parameter must match the variable name in Staff class.
	    idCol.setCellValueFactory(cellData -> cellData.getValue().employeeIDProperty());
	    nameCol.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
	    costCenterCol.setCellValueFactory(cellData -> cellData.getValue().costCenterProperty());
	    emailCol.setCellValueFactory(cellData -> cellData.getValue().emailAddressProperty());
	    dateInCol.setCellValueFactory(cellData -> cellData.getValue().dateJoinProperty());
	    dateOutCol.setCellValueFactory(cellData -> cellData.getValue().dateLeftProperty());
	    
	    //Cell Edit Factory. Setting Action for all String columns in the table.
	    for (TableColumn<Staff, String> col : stringColumns) {
	    	col.setCellFactory(TextFieldTableCell.forTableColumn());
	    	col.setOnEditCommit(e-> OnStaffCellEdit(e));
	    }
		
	    for (TableColumn<Staff, LocalDate> col : dateColumns) {
	    	col.setCellFactory(columnData -> StaffDatePickerCreate(columnData));
	    	col.setEditable(true);
	    }
	    
	    //Adding the date columns
	    boolean addAll = mTable.getColumns().addAll(idCol, nameCol, costCenterCol, emailCol, dateInCol, dateOutCol);
		if (!addAll) {
	    	//error
	    	return false;
	    }
	    
	    mTable.setItems(data);
	    
	    mTableLabel = new Label("Staff List");
	    mTableLabel.setFont(new Font("Arial", 20));

	    mLoadFile = new Button(LOADFILTER);
	    mLoadFile.setOnAction(e ->OnLoadFilterButtonClicked(e));
		
	    
	    //Construct the Save Button
	    HBox buttonsBox = new HBox(10);
	    buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
	    
		mMultiButton = new Button(EDIT);
		mMultiButton.setOnAction(e ->OnEditButtonClicked(e));
		
		mAddRowButton = new Button(ADD);
		mAddRowButton.setDisable(true);
		mAddRowButton.setOnAction(e ->OnAddButtonClicked(e));
		
		mDeleteRowButton =  new Button(DELETE);
		mDeleteRowButton.setDisable(true);
		mDeleteRowButton.setOnAction(e ->OnDeleteButtonClicked(e));
		
		mCancelButton = new Button (CANCEL);
		mCancelButton.setDisable(true);
		mCancelButton.setOnAction(e ->OnCancelButtonClicked(e));
		
		buttonsBox.getChildren().addAll(mMultiButton, mAddRowButton, mDeleteRowButton, mCancelButton );

	    mTableBox.setSpacing (5);
	    mTableBox.setPadding(new Insets(10, 0, 0, 10));
        mTableBox.getChildren().addAll(mTableLabel, mLoadFile, mTable, buttonsBox);
        
        //Populate Table with InitialData
        LoadTable ("conf/staffList2.xml");
        
		return true;
	}
	

	private TableCell<Staff,LocalDate> StaffDatePickerCreate(TableColumn<Staff, LocalDate> cellData) {
			StaffDatePicker<Staff,LocalDate> datePick = new StaffDatePicker<Staff,LocalDate>(data);
			return datePick;
	}
	
	public boolean LoadTable (String filename) {
		try {
			Xml xml = new Xml (filename);
			ArrayList<String> newData = xml.ReadFileToTable(mTable.getColumns());
			data.clear();
			for (int i=0; i<newData.size(); i++) {
				Staff staff = new Staff(newData.get(i));
				data.add(staff);
			}
			updateCostCenters();
			return true;
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	private void OnLoadFilterButtonClicked(ActionEvent e) {
		LoadTable ("conf/staffList.xml");
	}

	//	//Action Events
	private void OnStaffCellEdit(Event e) {
		//System.out.println("what is this? " + this.getClass());
		CellEditEvent<Staff,String> t = (CellEditEvent<Staff,String>) e;
		Staff staff = t.getTableView().getItems().get(t.getTablePosition().getRow());
		String columnName = t.getTablePosition().getTableColumn().getText();
		
		switch (columnName) { 
			case STAFFID_COL:
				staff.setEmployeeId(t.getNewValue());
				break;
			case STAFFCOSTCENTER_COL:
				staff.setCostCenter(t.getNewValue());
				break;
			case STAFFNAME_COL:
				staff.setFullName(t.getNewValue());
				break;
			case STAFFEMAIL_COL:
				staff.setEmailAddress(t.getNewValue());
				break;
			default:
				//Unsupported Cell Edit!
		}
	}

	public boolean updateCostCenters() {
		if (mCostCenters == null) {
			mCostCenters = new ArrayList<String>();
		}	
		mCostCenters.clear();
		for (Staff row : data) {
			String cc = row.getCostCenter();
			if (!mCostCenters.contains(cc)) {
				mCostCenters.add(cc);
			}
		}
		return true;
	}
	
	public ArrayList<String> getCostCenters () {
		return mCostCenters;
	}
	
	private void OnEditButtonClicked(ActionEvent e) {
		if (mMultiButton.getText() == EDIT) {
			mTable.setEditable(true);
			
			mMultiButton.setText(SAVE);
			mAddRowButton.setDisable(false);
			mCancelButton.setDisable(false);
			mDeleteRowButton.setDisable(false);	
			
			//editCopy = data;
			
		}
		else {
			mTable.setEditable(false);
			mMultiButton.setText(EDIT);
			mAddRowButton.setDisable(true);
			mCancelButton.setDisable(true);
			mDeleteRowButton.setDisable(true);
			WriteToFile();
		}
	}
	
	private void OnAddButtonClicked(ActionEvent e) {
		//Add a row
		//LocalDate defDate = new LocalDate(1,1,2015);
		data.add(new Staff("new entry","new entry","new entry","new entry",null,null));
	}
	

	private void OnDeleteButtonClicked(ActionEvent e) {
		Staff selectedPerson = mTable.getSelectionModel().getSelectedItem();
		data.remove(selectedPerson);
	}
	
	
	private void OnCancelButtonClicked(ActionEvent e) {
		//Discard all changes since last save.
		mTable.setEditable(false);
		for (TableColumn<Staff,LocalDate> col : dateColumns) {
			col.setEditable(true);
		}
		mMultiButton.setText(EDIT);
		mAddRowButton.setDisable(true);
		mCancelButton.setDisable(true);
		mDeleteRowButton.setDisable(true);
	}
	
	public boolean WriteToFile() {
		Xml xml = new Xml("staffList.xml");
		try {
			xml.WriteTableToFile(mTable);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//xml.WriteToFile();	
		return false;
	}
	
	//Accessors
	public VBox GetTableBox() { return mTableBox; }
	public ObservableList<Staff> getData() {return data;}
}


