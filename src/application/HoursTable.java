package application;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import data.HoursCC;
import statistics.HoursManager;
import files.Csv;
import files.Xsl;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class HoursTable extends DataScape {
	private static final String LOAD = "LOAD";
	private static final String SAVE = "SAVE";
	private static final String EDIT = "EDIT";
	private static final String ADD = "ADD";
	private static final String DELETE = "DELETE";
	private static final String CANCEL = "CANCEL";

	public static final String SENDINGCOSTCENTER_COL = "Sending Cost Center ID";
	public static final String DATELOGGED_COL = "Date of hours";
	public static final String DATESUBMITTED_COL = "Date submitted";
	public static final String EMPLOYEEID_COL = "PersonnelID";
	public static final String FULLNAME_COL = "Personnel Name";
	public static final String SUBPROJECTID_COL ="Sub Project ID";
	public static final String SUBPROJECTNAME_COL = "Sub Project Name";
	public static final String PROJECTGROUP_COL = "Project Group";
	public static final String SALESTYPE_COL = "Sales Type";
	public static final String RECEIVINGCOSTCENTER_COL = "Receiving Cost Center ID";
	public static final String HOURS_COL = "Hours";

	
	/***********************************************************************************************
	 * The Staff Table has a Label, a Table and a HBox of Buttons, all vertically stored a VBox
	 * *********************************************************************************************
	 */
	//UI Contents
	private VBox mTableBox;
	private TableView <HoursCC> mTable;
	private Button mLoadButton;
	private Button mTestButton;
	
	//Utilities
	
	//Data
	private HoursManager mHoursManager = new HoursManager(); 
	private ObservableList<HoursCC> data = FXCollections.observableArrayList();
	
	public HoursTable(Tableau plug) {
		super(plug);
	}

	@Override
	public boolean Initialize () throws IOException {
		System.out.println(this.getClass().toString());
			
		TableColumn<HoursCC, String> sendingcostcentercol = new TableColumn<HoursCC,String>(SENDINGCOSTCENTER_COL);		
	    TableColumn<HoursCC, String> employeeidcol = new TableColumn<HoursCC, String>(EMPLOYEEID_COL);
	    TableColumn<HoursCC, String> fullnamecol = new TableColumn<HoursCC, String>(FULLNAME_COL);
	    TableColumn<HoursCC, String> subprojectidcol = new TableColumn<HoursCC, String>(SUBPROJECTID_COL);
	    TableColumn<HoursCC, String> subprojectnamecol = new TableColumn<HoursCC, String>(SUBPROJECTNAME_COL);
	    TableColumn<HoursCC, String> projectgroupcol = new TableColumn<HoursCC, String>(PROJECTGROUP_COL);
	    TableColumn<HoursCC, String> salestypecol = new TableColumn<HoursCC, String>(SALESTYPE_COL);
	    TableColumn<HoursCC, String> receivingcostcentercol = new TableColumn<HoursCC, String>(RECEIVINGCOSTCENTER_COL);
	    TableColumn<HoursCC, LocalDate> dateloggedcol = new TableColumn<HoursCC, LocalDate>(DATELOGGED_COL);
	    TableColumn<HoursCC, LocalDate> datesubmittedcol = new TableColumn<HoursCC, LocalDate>(DATESUBMITTED_COL);
	    TableColumn<HoursCC, Double> hourscol = new TableColumn<HoursCC, Double>(HOURS_COL);
	    
	    
	    //Cell Value Factory. The input parameter must match the variable name in Staff class.
	    sendingcostcentercol.setCellValueFactory(cellData -> cellData.getValue().SendingCostCenterProperty());
	    employeeidcol.setCellValueFactory(cellData -> cellData.getValue().employeeIDProperty());
	    fullnamecol.setCellValueFactory(cellData -> cellData.getValue().fullNameProperty());
	    subprojectidcol.setCellValueFactory(cellData -> cellData.getValue().subProjectIdProperty());
	    subprojectnamecol.setCellValueFactory(cellData -> cellData.getValue().subProjectNameProperty());
	    projectgroupcol.setCellValueFactory(cellData -> cellData.getValue().projectGroupProperty());
	    salestypecol.setCellValueFactory(cellData -> cellData.getValue().salesTypeProperty());
	    receivingcostcentercol.setCellValueFactory(cellData -> cellData.getValue().receivingCostCenterProperty());
	    dateloggedcol.setCellValueFactory(cellData -> cellData.getValue().dateLoggedProperty());
	    datesubmittedcol.setCellValueFactory(cellData -> cellData.getValue().dateSubmittedProperty());
	    hourscol.setCellValueFactory(cellData -> cellData.getValue().hoursProperty().asObject());
	     
	    	    
	    mTable = new TableView<HoursCC>();
	    mTable.getColumns().addAll(sendingcostcentercol,dateloggedcol,datesubmittedcol,employeeidcol,fullnamecol,
	    							subprojectidcol,subprojectnamecol,projectgroupcol,salestypecol,receivingcostcentercol, hourscol);
	    
	    mTable.setItems(data);

	    HBox buttonsBox = new HBox();
	    mLoadButton = new Button(LOAD);
	    try {
			mLoadButton.setOnAction(e->OnLoadButtonClicked(e));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    mTestButton = new Button(EDIT);
	    mTestButton.setOnAction(e ->OnTestButtonClicked(e));
	    
	    
	    buttonsBox.getChildren().addAll(mLoadButton, mTestButton);
	    buttonsBox.setAlignment(Pos.BOTTOM_RIGHT);
	    	    
	    mTableBox = new VBox();
	    mTableBox.getChildren().addAll(mTable, buttonsBox);
	    
	    //Initialize table from default file
	    LoadTable ("conf/hours.xlsx");
	    double myHours = mHoursManager.QueryTotalHours("3511", 0, 0);
	    
	    System.out.println("My Hours = " + myHours);
	    
		return true;
	}
		
	public boolean LoadTable (String filename) throws IOException {
		ObservableList<HoursCC> thisdata = mHoursManager.LoadFile(filename,this.GetColumns());
		mTable.setItems(thisdata);
		return true;
		
	}
	
	private Object OnLoadButtonClicked(ActionEvent e)  {
		// TODO Auto-generated method stub
		try {
			LoadTable ("conf/hours.xlsx");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	
	private Object OnTestButtonClicked(ActionEvent e) {
		
		double hoursIC = mHoursManager.SumHoursByMonth ("3511", Month.MARCH, "IC");
		double hoursADM = mHoursManager.SumHoursByMonth ("3511", Month.MARCH, "ADM");
		double hoursABS = mHoursManager.SumHoursByMonth ("3511", Month.MARCH, "ABS");
	
		System.out.println("Total = " + (hoursIC + hoursADM + hoursABS));
		return null;
	}
	
  
    
    public ArrayList<String> GetColumns () {
    	
		//NEED TO FIX THIS
		ArrayList<String> cols = new ArrayList<String>();
		cols.add(SENDINGCOSTCENTER_COL);
		cols.add(DATELOGGED_COL);
		cols.add(DATESUBMITTED_COL);
		cols.add(EMPLOYEEID_COL);
		cols.add(FULLNAME_COL);
		cols.add(SUBPROJECTID_COL);
		cols.add(SUBPROJECTNAME_COL);
		cols.add(PROJECTGROUP_COL);
		cols.add(SALESTYPE_COL);
		cols.add(RECEIVINGCOSTCENTER_COL);
		cols.add(HOURS_COL);
		
		return cols;
    }

	public VBox getTableBox() {
		return mTableBox;
	}
	
	public HoursManager getHoursManager() {
		return mHoursManager;
	}
}
