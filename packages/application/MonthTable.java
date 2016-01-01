package application;

import data.HoursCC;
import data.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import java.text.DateFormatSymbols;

public class MonthTable extends DataPlug {
	public static final String DAY_COL = "Day";
	public static final String DATE_COL = "Date";
	
	
	/***********************************************************************************************
	 * The Staff Table has a Label, a Table and a HBox of Buttons, all vertically stored a VBox
	 * *********************************************************************************************
	 */
	//UI Contents
	private VBox mTableBox;
	private TableView mTable;
	private Button mLoadButton;
	
	
	//Utilities
	
	//Data
	//private ObservableList<HoursCC> data = FXCollections.observableArrayList();
	
	//Constructor
	public MonthTable() {
		mTableBox = new VBox();
	}
	
	@Override
	public boolean Initialize() {
		//The first HBOX will provide interface to constructor the tablebox.
		//It has a dropdown list to select months
		//It has a dropdown list to select cost center
		
		ComboBox<String> monthComboBox = new ComboBox<>();
		monthComboBox.getItems().addAll(new DateFormatSymbols().getMonths());
		//default month is current month
		
		
		
		mTableBox.getChildren().addAll(monthComboBox);
		
		return false;
	}
	
	//this should be in utilities.
	public String getMonth(int month) {
	    return new DateFormatSymbols().getMonths()[month-1];
	}
	
	public VBox getTableBox() { return mTableBox; }

}
