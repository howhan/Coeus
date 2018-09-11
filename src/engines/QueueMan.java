package engines;

import java.util.Queue;
import data.Transaction;

// A transaction engine is a huge queue which will execute tasks in a queue order. 
// It asynchronously collects transactions and executes the tasks in a transaction. 
// A
public class QueueMan extends Plug {
	
	private Queue<Transaction> mQueue;
	
	public QueueMan (Queue<Transaction> queue) {
		mQueue = queue;
	}
	
}
