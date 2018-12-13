package yogi.cluster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;
import yogi.base.app.multithread.BaseTask;
import yogi.base.app.multithread.ThreadPoolExecutor;
import yogi.base.indexing.ManyIndexer;
import yogi.base.util.Timer;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.logging.Logging;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;

public abstract class BaseClusterController implements ClusterController {
	public static int FlushSleepTimeInSeconds = 1000;
	private Logger logger = Logging.getLogger(BaseClusterController.class);
	protected Map<String, CommandExecutor> serverCommandExecutors;
	protected static ManyIndexer<String, ClusterCommand> resendCommands = new ManyIndexer<String, ClusterCommand>();
	protected static Map<String, Timer> timersByServer  = new HashMap<String, Timer>();
	
	public BaseClusterController(Map<String, CommandExecutor> serverCommandExecutors) {
		super();
		this.serverCommandExecutors = serverCommandExecutors;
	}

	public void flush(){
		while(!resendCommands.values().isEmpty()){
			try {
				Thread.sleep(FlushSleepTimeInSeconds);
				logger.info(String.format("Waiting for %s milli seconds to flush cluster commands", FlushSleepTimeInSeconds));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected synchronized void processResendCommands(ClusterCommand command) {
		if(serverCommandExecutors.size() > 1){
			ThreadPoolExecutor threadPoolExecutor= new ThreadPoolExecutor(serverCommandExecutors.size());
			for(Entry<String, CommandExecutor> entry: serverCommandExecutors.entrySet())
			{
				CommandExecutorEntryTask task = getTask(entry, command);
				threadPoolExecutor.execute(task);
			}
			threadPoolExecutor.waitForTaskCompletion();
		}else{
			for(Entry<String, CommandExecutor> entry: serverCommandExecutors.entrySet())
			{
				CommandExecutorEntryTask.execute(entry , command, ErrorReporter.get());
			}
		}
	}

	protected static Timer getTimer(String server){
		Timer timer = timersByServer.get(server);
		if(timer == null){
			timer = new Timer();
			timersByServer.put(server, timer);
		}
		return timer;
	}
	
	public static class CommandExecutorEntryTask extends BaseTask
	{
		private Entry<String, CommandExecutor> entry;
		private ClusterCommand command;
		public CommandExecutorEntryTask(Entry<String, CommandExecutor> entry, ClusterCommand command, ErrorReporter errorReporter) {
			super(errorReporter);
			this.entry = entry;
			this.command = command;
		}

		public void run() {
			super.run();
			execute(entry, command, getErrorReporter());
		}

		public static void execute(Entry<String, CommandExecutor> entry, ClusterCommand command, ErrorReporter errorReporter) {
			ImmutableList<ClusterCommand> commandsToResend = null;
			synchronized(resendCommands){
				commandsToResend = resendCommands.get(entry.getValue().getServerType());
			}
			Level logLevel = Level.SEVERE;
			if(!commandsToResend.isEmpty()){
				try {
					if(command != null){
						synchronized(resendCommands){
							resendCommands.index(entry.getValue().getServerType(), command);
						}
					}
					Integer i = entry.getValue().execute(new ClusterCommands(new ArrayList<ClusterCommand>(commandsToResend)));
					if(i != 0){
						synchronized(resendCommands){
							resendCommands.remove(entry.getValue().getServerType());
							resendCommands.index(entry.getValue().getServerType(), commandsToResend.subList(i, commandsToResend.size()));
						}
					}
					if(i != commandsToResend.size()){
						errorReporter.error("Could not successfully process resendCommands to "+ entry.getValue().getServerType());
					}
				} catch (CommandException e) {
					Timer timer = getTimer(entry.getKey());
					if(timer.process()){
						errorReporter.log(logLevel, e.getMessage(), e);
					}
				}
			}else if(command != null){
				try {
					Boolean execute = entry.getValue().execute(command);
					if(!execute)
					{
						throw new CommandException("Could not successfully process command to " + entry.getValue().getServerType());
					}
				} catch (CommandException e) {
					synchronized(resendCommands){
						resendCommands.index(entry.getValue().getServerType(), command);
					}
					Timer timer = getTimer(entry.getKey());
					if(timer.process()){
						errorReporter.log(logLevel, e.getMessage(), e);
					}
				}
			}
		}
	}

	public CommandExecutorEntryTask getTask(Entry<String, CommandExecutor> entry, ClusterCommand command) {
		return new CommandExecutorEntryTask(entry, command, ErrorReporter.get());
	}
	
}
