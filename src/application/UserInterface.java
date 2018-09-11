package application;

import java.io.IOException;

import application.Tableau;
import application.StaffTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserInterface extends Tableau {
	private StaffTable mStaffTable;
	private HoursTable mHoursTable;
	private MonthTable mMonthTable;

	
	/******************************************************************************************
	 * The main user interface will be a border pane layout with Top Left Center Right Bottom. 
	 * The top pane will be menu bar with a search / navigation text box
	 * The left pane will be an explorer window.
	 * The center pane will be the main display of the contents
	 *******************************************************************************************/
	public UserInterface (int width, int height) {
		//default values
		mTitle = "COEUS Line Management Application";
		
		mScene = new Scene (new BorderPane(), width, height);
		if (BorderLayout() != null) {
			BorderLayout().setTop(new GridPane());
			BorderLayout().setCenter(new StackPane());
			BorderLayout().setBottom(new GridPane());
		}

		TabPane tabPane = new TabPane();
		
		mStaffTable = new StaffTable(this);
		mStaffTable.Initialize();
		Tab staffTableTab = new Tab("Staff");
		staffTableTab.setContent(mStaffTable.GetTableBox());
		
		
		mHoursTable = new HoursTable(this);
		try {
			mHoursTable.Initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tab hoursTableTab = new Tab("Hours CC");
		hoursTableTab.setContent(mHoursTable.getTableBox());
		
		mMonthTable = new MonthTable(this);
		mMonthTable.Initialize();
		Tab monthTableTab = new Tab("Month View");
		monthTableTab.setContent(mMonthTable.getTableBox());
		
		
		
		tabPane.getTabs().addAll(staffTableTab, hoursTableTab, monthTableTab);
		
		System.out.println("Free CPU " + GetSystemMan().getFreeCPU());
		
		

		CenterPane().getChildren().add(tabPane);
		
//		Button btn = new Button("Test");
//		HBox nextButton = new HBox(10);
//		nextButton.setAlignment(Pos.BOTTOM_RIGHT);
//		nextButton.getChildren().add(btn);
//		root.getChildren().addAll(nextButton);
//		btn.setOnAction(e-> TestButtonClicked(e));
	}

	private GridPane TopPane() {
		if (mScene != null) {
			return (GridPane)(BorderLayout().getTop());
		}
		return null;
	}
	
	private GridPane BottomPane() {
		if (mScene != null) {
			return (GridPane)(BorderLayout().getBottom());
		}
		return null;
	}
	
	private StackPane CenterPane() {
		if (mScene != null) {
			return (StackPane)(BorderLayout().getCenter());
		}
		return null;
	}
	
	private BorderPane BorderLayout() { 
		if (mScene != null) {
			return (BorderPane)(mScene.getRoot());
		}
		return null;
	}
	
	public void TestButtonClicked (ActionEvent e) {
		
	
	}
	
	public StaffTable getStaffTable() { return mStaffTable; }
	public HoursTable getHoursTable() { return mHoursTable; }
	public MonthTable getMonthTable() { return mMonthTable; }
	
	public int LoadConfiguration () {
		//TODO: loading the user interface properties from a file.
		return 1;
	}
	

}

