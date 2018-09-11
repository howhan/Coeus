package LineManagementApp;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

import application.LoginForm;
import application.Tableau;
import application.UserInterface;
import engines.SystemMan;

public class LineManagementApp extends Application {
	
    @Override
    public void start (Stage primaryStage) {
    	Tableau.SetStage(primaryStage);
    	Tableau.SetSystemMan(new SystemMan());
    
    	//create login form and initialize to configuration File
    	LoginForm loginForm = new LoginForm();
    	loginForm.Initialize();
    	
    	UserInterface ui = new UserInterface (1024,768);
    	loginForm.SetNextScene(ui.GetScene());
    
	    primaryStage.setTitle(loginForm.GetTitle());
	    primaryStage.setScene(loginForm.GetScene());
	    primaryStage.show();
    }
   
	public static void main(String[] args) {
		//main application codes start here.
		launch (args);
	} 
}
