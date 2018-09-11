package application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import application.SystemMan;

/* This class is the base Scene class for this application.
 * All forms of Scenes in the User
 */
public class Tableau {
	private static Stage thisStage;
	private static SystemMan sSystemMan;
	protected Scene mScene;
	protected Scene mNextScene;
	protected String mTitle;

	public Tableau() {
	}

	public boolean Initialize() {
		return false;
	}

	// Accessors
	public static void SetStage(Stage stage) {
		thisStage = stage;
	}

	public static void SetSystemMan(SystemMan systemMan) {
		sSystemMan = systemMan;
	}

	public void SetTitle(String title) {
		mTitle = title;
	}

	public void SetScene(Scene scene) {
		mScene = scene;
	}

	public static Stage GetStage() {
		return thisStage;
	}

	public static SystemMan GetSystemMan() {
		return sSystemMan;
	}

	public Scene GetScene() {
		return mScene;
	}

	public String GetTitle() {
		return mTitle;
	}

}
