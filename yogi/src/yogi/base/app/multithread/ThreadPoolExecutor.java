package yogi.base.app.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor{
	public static int NumberOfThreads = 2;
	private ExecutorService threadPool;
	
	public ThreadPoolExecutor() {
		this(NumberOfThreads);
	}
	public ThreadPoolExecutor(int numberOfThreads) {
		super();
		threadPool = Executors.newFixedThreadPool(numberOfThreads);
	}
	
	public void shutdown(){
		threadPool.shutdown();
	}
	
	public boolean waitForTaskCompletion()
	{
		threadPool.shutdown();
		boolean awaitTermination = false;
		try {
			awaitTermination = threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return awaitTermination;
	}

	public void execute(Task task) {
		threadPool.execute(task);
	}
}
