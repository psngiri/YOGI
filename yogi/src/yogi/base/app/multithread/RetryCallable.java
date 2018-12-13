package yogi.base.app.multithread;

import yogi.base.app.ErrorReporter;

public abstract class RetryCallable<T> implements Callable<T> {
	public static int RetryAttempts = 3;
	
	public void call(T item) {
		int attempts = 1;
		boolean retry = retryCall(item, attempts);
		while(retry && attempts < RetryAttempts)
		{
			attempts ++;
			ErrorReporter.get().info(String.format("Trying %s attempt for %s", attempts, item));
			retry = retryCall(item, attempts);
		}
		if(attempts == RetryAttempts) ErrorReporter.get().error("Could not process even after " +  RetryAttempts + " retry attempts", item);
	}

	protected abstract boolean retryCall(T item, int attempt);

}
