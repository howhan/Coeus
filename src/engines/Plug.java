package engines;

// Base class for all engine classes
// All engines are by default running on their own thread.
// 
public class Plug {
	private static final int OK = 0;
	private String mName;
	private String mMessage;
	private double mLogInterval;
	
	public void SetName (String name) { mName = name; }
	public String GetName () { return mName; }
	
	public int Start() {
		//This command should start the engine to be running asynchronously all the time
		return OK;
	}
	
}
