package yogi.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import yogi.base.app.ErrorReporter;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.ThreadPoolExecutor;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.CommandExecutor;

public class Mapper {
	private Map<String, CommandExecutor> serverCommandExecutors;

	public Mapper(Map<String, CommandExecutor> serverCommandExecutors) {
		super();
		this.serverCommandExecutors = serverCommandExecutors;
	}

	public  <R> R execute(CommandAdapter<R> command, Reducer<R> reducer) {
		if(serverCommandExecutors.size() < 2){
			throw new RuntimeException("Please Configure atleast 2 Servers to Map to in MapReduceManager, at present: "+ serverCommandExecutors.keySet());
		}
		ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(serverCommandExecutors.size());
		List<CommandTask<R>> tasks = new ArrayList<CommandTask<R>>(serverCommandExecutors.size());
		for(Entry<String, CommandExecutor> entry: serverCommandExecutors.entrySet())
		{
			CommandTask<R> task = getTask(entry, command);
			tasks.add(task);
			threadPoolExecutor.execute(task);
		}
		threadPoolExecutor.waitForTaskCompletion();
		
		List<R> results = new ArrayList<R>(tasks.size());
		for(CommandTask<R> task: tasks){
			R result = task.getResult();
			if(result != null){
				results.add(result);				
			}
		}
		return reducer.reduce(results);
	}
	
	static class CommandTask<R> extends BaseTask
	{
		private CommandAdapter<R> command;
		private Entry<String, CommandExecutor> entry;
		private R result;
		
		public CommandTask(Entry<String, CommandExecutor> entry, CommandAdapter<R> command, ErrorReporter errorReporter) {
			super(errorReporter);
			this.entry = entry;
			this.command = command;
		}

		public void run() {
			super.run();
			try {
				result = entry.getValue().execute(command);
			} catch (Throwable e) {
				getErrorReporter().error("Error in Mapping to Server: "+ entry.getKey(), e.getMessage(), e);
			}
		}

		R getResult() {
			return result;
		}
		
	}

	public <R> CommandTask<R> getTask(Entry<String, CommandExecutor> entry, CommandAdapter<R> command) {
		return new CommandTask<R>(entry, command, ErrorReporter.get());
	}

}
