package application;

import application.Tableau;
import javafx.scene.control.TreeView;

public class ManagementTree extends Tableau{
	private TreeView mTreeView;

	public ManagementTree() {
		mTreeView = new TreeView();
	}
	
	public boolean Initialize() {
		
		return true;
	}
}
