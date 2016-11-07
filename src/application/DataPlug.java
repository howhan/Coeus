package application;

import java.io.IOException;

public class DataPlug {

	private Plug mContainer;
	public DataPlug(Plug plug) {mContainer = plug;}
	
	public boolean Initialize () throws IOException {
		return false;
	}
	
	public Plug getContainer () { return mContainer; }
}
