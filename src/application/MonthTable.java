package application;

import data.HoursCC;
import data.Staff;
import data.Utilities;
import application.UserInterface;
import application.HoursTable;
import application.StaffTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class MonthTable extends DataPlug {
	public static final String DAY_COL = "Day";
	public static final String DATE_COL = "Date";
	public static final String COSTCENTER_ALL = "<All>";
	
	
	/***********************************************************************************************
	 * The Staff Table has a Label, a Table and a HBox of Buttons, all vertically stored a VBox
	 * *********************************************************************************************
	 */
	//UI Contents
	private VBox mTableBox;
	private TableView <String> mTable;
	private Button mLoadButton;
	private ComboBox<String> monthComboBox;
	private ComboBox<String> costCenterComboBox;
	//Data
	//private ObservableList<HoursCC> data = FXCollections.observableArrayList();

	
	//Constructor
	public MonthTable(Plug plug) {
		super(plug);
		mTableBox = new VBox();
		mTable = new TableView<String>();
	}
	
	@Override
	public boolean Initialize() {
		//The first HBOX will provide interface to constructor the tablebox.
		//It has a dropdown list to select months
		//It has a dropdown list to select cost center
		
		Calendar now = Calendar.getInstance();
		
		
		monthComboBox = new ComboBox<>();
		monthComboBox.getItems().addAll(new DateFormatSymbols().getMonths());
		//TODO: default month is current month
		monthComboBox.setValue(Utilities.Instance().MonthNumberToString(now.get(Calendar.MONTH)+1));
		
		costCenterComboBox = new ComboBox<>();
		costCenterComboBox.getItems().addAll(StaffTable().getCostCenters());
		costCenterComboBox.setValue(COSTCENTER_ALL);
		costCenterComboBox.setOnMouseClicked (e->onCostCenterContextMenuRequest(e));
		
		HBox hbox = new HBox();
		hbox.getChildren().addAll(monthComboBox, costCenterComboBox);
		
		mTableBox.getChildren().addAll(hbox, mTable);
		
		//Table Initialization
		BuildTable(monthComboBox.getValue(),costCenterComboBox.getValue());
		
		
		return false;
	}
	
	private Object onCostCenterContextMenuRequest(MouseEvent e) {
		// TODO Auto-generated method stub
		costCenterComboBox.getItems().clear();
		costCenterComboBox.getItems().add(COSTCENTER_ALL);
		if (StaffTable() != null) {
			costCenterComboBox.getItems().addAll(StaffTable().getCostCenters());
		}
		return null;
	}

	public boolean BuildTable(String month, String costCenteer) {
		//default tableview will be all the employees in the StaffTable.
		ObservableList<Staff> staffList = StaffTable().getData();
		TableColumn<String, String> dateCol = new TableColumn<String, String>("Date");
		mTable.getColumns().add(dateCol);
		for (Staff row : staffList) {
			mTable.getColumns().add(new TableColumn<String,String>(row.getFullName()));
		}
		
		return false;
	}
	
	private HoursTable HoursTable() {
		UserInterface ui = (UserInterface) this.getContainer();
		return ui.getHoursTable();
	}
	
	private StaffTable StaffTable() {
		//TODO: code to handle staffTable if staffTable is still loading?
		UserInterface ui = (UserInterface) this.getContainer();
		return ui.getStaffTable();
	}
	
	public VBox getTableBox() { return mTableBox; }

}
