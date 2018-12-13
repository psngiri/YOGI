package yogi.base.app.multithread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;


public class BaseMultiCaller<T, C extends Callable<T>> {
	private List<C> callables = new ArrayList<C>();
	
	public List<C> getCallables() {
		return callables;
	}

	public void addCallable(C callable) {
		this.callables.add(callable);
	}

	public void call(Collection<T> items)
	{
		if(callables.size() == 1)
		{
			doSingleThreadExecution(items);
		}else
		{
			doMultiThreadExecution(items);
		}
	}

	private void doMultiThreadExecution(Collection<T> items) {
		Iterator<T> iterator = items.iterator();
		int numberOfThreads = callables.size();
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(numberOfThreads);
		for(int i = 0; i < numberOfThreads; i++)
		{
			Callable<T> callable = callables.get(i);
			threadPoolExecutor.execute(new CallerTask<T>(iterator, callable, ErrorReporter.get()));
		}
		threadPoolExecutor.waitForTaskCompletion();
	}

	private void doSingleThreadExecution(Collection<T> items)
	{
		Callable<T> callable = callables.get(0);
		if(!callable.open()) return;
		for(T item: items)
		{
			Executor.get().execute(callable, item);
		}
		callable.close();
	}
	
	static class CallerTask<T> extends BaseTask
	{
		Iterator<T> iterator;
		Callable<T>  callable;
		
		public CallerTask(Iterator<T> iterator, Callable<T> callable, ErrorReporter errorReporter) {
			super(errorReporter);
			this.iterator = iterator;
			this.callable = callable;
		}

		public void run() {
			try{
				super.run();
				if(!callable.open()) return;
				Executor executor = Executor.get();
				T nextItem = getNext();
				while(nextItem!= null)
				{
					try {
						executor.execute(callable, nextItem);
					} catch (Throwable e) {
						errorReporter.error("Error in MultiCaller", e.getMessage(), e);
					}
					nextItem = getNext();
				}
				callable.close();
			}catch (Exception e) {
				errorReporter.error("Error in MultiCaller", e.getMessage(), e);
			}
		}
		
		public T getNext()
		{
			T rtnValue = null;
			synchronized(iterator)
			{
				if(iterator.hasNext()) 
				{
					rtnValue = iterator.next();
				}
			}
			return rtnValue;
		}
		
	}
}
