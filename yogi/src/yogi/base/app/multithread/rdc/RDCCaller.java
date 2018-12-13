package yogi.base.app.multithread.rdc;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.ThreadPoolExecutor;
import yogi.base.app.multithread.rdc.command.RDCCallerCommand;
import yogi.base.network.ServerCommandExectuorManager;
import yogi.base.util.collections.IndexItem;
import yogi.base.util.logging.Logging;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;


public class RDCCaller<T, R, C extends RDCCallable<T, R>> {
	public static int NumberOfThreads = 2;
	private int numberOfThreads;
	private static Logger logger = Logging.getLogger(RDCCaller.class);
	
	public RDCCaller() {
		this(NumberOfThreads);
	}

	public RDCCaller(int numberOfThreads) {
		super();
		this.numberOfThreads = numberOfThreads;
	}

	public Collection<R> call(Collection<T> items, Class<C> klass, Object... parameters)
	{
		List<C> callables = getCallables(klass, parameters);	
		if(callables.size() == 1)
		{
			return doSingleThreadExecution(items, callables);
		}else
		{
			return doMultiThreadExecution(items, callables);
		}
	}
	
	public RDCList<T, R> call(String userId, Collection<T> items, Class<C> klass, Object... parameters) {
		RDCList<T, R> rdcList = new RDCList<T,R>(items);
		RDCCallerCommand<T,R,C> command = new RDCCallerCommand<T,R,C>(userId, rdcList.getRDCListIterator(), klass, parameters);
		Executor.get().execute(command);
		return rdcList;
	}
	
	
	private  C getCallable(Class<C> klass, Object... parameters) {
		C callable = null;
		try {
			Class<?>[] parameterTypes = new Class[parameters.length];
			for(int i = 0; i < parameters.length; i ++ ){
				parameterTypes[i] = Object.class;
			}
			Constructor<C> constructor = klass.getConstructor(parameterTypes);
			callable = constructor.newInstance(parameters);
		} catch (Exception e) {
			throw new RuntimeException("Could not construct class: " + klass.getCanonicalName(), e);
		} 
		return callable;
	}

	public void distribute(RDCCallerCommand<T,R,C> command)
	{
		for(Entry<String, CommandExecutor> entry: ServerCommandExectuorManager.get().getServerCommandExecutors().entrySet()){
			try {
				entry.getValue().execute(command);
			} catch (CommandException e) {
//				e.printStackTrace();
				if(logger.isLoggable(Level.FINE)){
					logger.fine("Could not forward command to server :"+ entry.getKey());
				}
			}
		}
	}
	
	private Collection<R> doMultiThreadExecution(Collection<T> items, List<C> callables) {
		RDCList<T, R> rdcList = new RDCList<T,R>(items);
		execute(rdcList.getRDCListIterator(), callables);
		return rdcList.getReturnValues();
	}
	
	public RDCList<T, R> execute(Collection<T> items, Class<C> klass, Object... parameters){
		List<C> myCallables = getCallables(klass, parameters);	
		RDCList<T, R> rdcList = new RDCList<T,R>(items);
		execute(rdcList.getRDCListIterator(), myCallables);
		return rdcList;
	}
	
	public void execute(RDCListIterator<T, R> iterator, Class<C> klass, Object... parameters){
		List<C> myCallables = getCallables(klass, parameters);	
		execute(iterator, myCallables);
	}
	
	private List<C> getCallables(Class<C> klass, Object... parameters) {
		List<C> myCallables = new ArrayList<C>(numberOfThreads);
		for(int i = 0; i < numberOfThreads; i++)
		{
			myCallables.add(getCallable(klass, parameters));
		}
		return myCallables;
	}

	private void execute(RDCListIterator<T, R> iterator, List<C> callables) {
		int numberOfThreads = callables.size();
		if(numberOfThreads < 1) return;
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(numberOfThreads);
		for(int i = 0; i < numberOfThreads; i++)
		{
			RDCCallable<T, R> callable = callables.get(i);
			threadPoolExecutor.execute(new CallerTask<T, R>(iterator, callable, ErrorReporter.get()));
		}
		threadPoolExecutor.shutdown();
	}

	private Collection<R> doSingleThreadExecution(Collection<T> items, List<C> callables)
	{
		RDCCallable<T, R> callable = callables.get(0);
		if(!callable.open()) return new ArrayList<R>(0);
		List<R> rtnValue = new ArrayList<R>(items.size());
		for(T item: items)
		{
			rtnValue.add(Executor.get().execute(callable, item));
		}
		callable.close();
		return rtnValue;
	}
	
	static class CallerTask<T, R> extends BaseTask
	{
		RDCListIterator<T, R> iterator;
		RDCCallable<T, R>  callable;
		
		public CallerTask(RDCListIterator<T, R> iterator, RDCCallable<T, R> callable, ErrorReporter errorReporter) {
			super(errorReporter);
			this.iterator = iterator;
			this.callable = callable;
		}

		public void run() {
			try {
				super.run();
				if(!callable.open()) return;
				Executor executor = Executor.get();
					IndexItem<T> nextItem = iterator.getNextIndexItem(null, null);
					while(nextItem!= null)
					{
						R rtnValue = null;
						try {
							rtnValue = executor.execute(callable, nextItem.getItem());
						} catch (Throwable e) {
							errorReporter.error("Error in MultiCaller", e.getMessage(), e);
						}
						nextItem = iterator.getNextIndexItem(nextItem, rtnValue);
					}
				callable.close();
			}catch (Exception e) {
				errorReporter.error("Error in MultiCaller", e.getMessage(), e);
			}
		}
		
		
	}
	
}
