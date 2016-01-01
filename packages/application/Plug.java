package application;

import javafx.scene.Scene;
import javafx.stage.Stage;

/* This class is the base Scene class for this application.
 * All forms of Scenes in the User
 */
public class Plug {
	private static Stage thisStage;
	protected Scene mScene;
	protected Scene mNextScene;
	protected String mTitle;
	
	public Plug() {}
	public boolean Initialize() {
		return false;
	}
	
	//Accessors
	public static void SetStage (Stage stage) { thisStage = stage; }
	public void SetTitle (String title) { mTitle = title; }   
	public void SetScene (Scene scene) { mScene = scene; }
	
	public static Stage GetStage () { return thisStage; }
	public Scene GetScene () { return mScene; }
	public String GetTitle() { return mTitle; }
	
}
