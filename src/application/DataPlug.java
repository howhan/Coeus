package application;

public class DataPlug {

	private Plug mContainer;
	public DataPlug(Plug plug) {mContainer = plug;}
	
	public boolean Initialize () {
		return false;
	}
	
	public Plug getContainer () { return mContainer; }
}
