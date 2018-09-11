package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import application.Tableau;

public class LoginForm extends Tableau{
	private GridPane mGrid;
	private Text mLoginMessage;
	private HBox mSignInButton;
	
	public LoginForm() {
		mTitle = "Login";
	}
	
	public void LoginButtonClicked (ActionEvent e) {
		//TODO: Verify login credentials
		Tableau.GetStage().setScene (mNextScene);
	}
	
	@Override
	public boolean Initialize() {
		//Create Scene
		mGrid = new GridPane();
		mGrid.setAlignment(Pos.CENTER);
		mGrid.setHgap(10);
		mGrid.setVgap(10);
		mGrid.setPadding(new Insets(25,25,25,25));
		mScene = new Scene (mGrid, 300, 275);
		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		mGrid.add(scenetitle, 0, 0, 2, 1);

		//Username field
		Label userName = new Label("User Name:");
		mGrid.add(userName, 0, 1);
		TextField userTextField = new TextField();
		mGrid.add(userTextField, 1, 1);

		//Password Field
		Label pw = new Label("Password:");
		mGrid.add(pw, 0, 2);
		PasswordField pwBox = new PasswordField();
		mGrid.add(pwBox, 1, 2);
		
		//Construct the Login Button
		Button btn = new Button("Sign in");
		HBox mSignInButton = new HBox(10);
		mSignInButton.setAlignment(Pos.BOTTOM_RIGHT);
		mSignInButton.getChildren().add(btn);
		mGrid.add(mSignInButton, 1, 4);		
		mLoginMessage = new Text();
        mGrid.add(mLoginMessage, 1, 6);
        btn.setOnAction(e-> LoginButtonClicked(e));
        
        return true;
	}
	
	
	
	public Scene GetScene() { return mScene; }
	public String GetTitle() { return mTitle; }
	public void SetNextScene(Scene scene) { mNextScene = scene; }
}
