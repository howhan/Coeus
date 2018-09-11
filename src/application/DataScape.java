package application;

import java.io.IOException;

public class DataScape {

	private Tableau mContainer;
	
	public DataScape(Tableau tableau) {mContainer = tableau;}
	
	public boolean Initialize () throws IOException {
		return false;
	}
	
	public Tableau getContainer () { return mContainer; }
	
	public boolean InitializeConfiguration() {
		
		return false;
		
	}
}
