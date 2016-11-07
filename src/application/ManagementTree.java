package application;

import application.Plug;
import javafx.scene.control.TreeView;

public class ManagementTree extends Plug{
	private TreeView mTreeView;

	public ManagementTree() {
		mTreeView = new TreeView();
	}
	
	public boolean Initialize() {
		
		return true;
	}
}
