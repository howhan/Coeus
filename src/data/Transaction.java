package data;

import java.util.ArrayList;
import data.Task;


//This class is the unit for transaction. 
//It is queued in the QueueMan.
public class Transaction {
	//A transaction has a collection of tasks. ArrayList is preferred as the list of tasks usually does not change.
	private ArrayList<Task> mTasks;
	
	public Transaction () {
		setTasks(new ArrayList<Task>());
	}

	public ArrayList<Task> getTasks() {
		return mTasks;
	}

	public void setTasks(ArrayList<Task> mTasks) {
		this.mTasks = mTasks;
	}
	
	public boolean Add (Task task) {
		return mTasks.add(task);
	}
}
