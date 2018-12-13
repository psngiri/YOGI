package yogi.cluster;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import yogi.base.app.ErrorReporter;
import yogi.base.app.multithread.BaseTask;
import yogi.base.util.scheduler.SchedulerAssistant;
import yogi.remote.client.app.CommandExecutor;

public class DelayedClusterController extends BaseClusterController {
	private ScheduledFuture<?> scheduledFuture = null;
	private BaseTask command = new BaseTask(ErrorReporter.get()){
		@Override
		public void run() {
			super.run();
			processResendCommands(null);
		}

	};
	private long clusterEveryseconds;
		
	public DelayedClusterController(Map<String, CommandExecutor> serverCommandExecutors, int clusterEveryseconds) {
		super(serverCommandExecutors);
		this.clusterEveryseconds = clusterEveryseconds;
	}

	@Override
	public void stop() {
		if(scheduledFuture != null) scheduledFuture.cancel(false);
	}

	@Override
	public void process(ClusterCommand command) {
		synchronized(resendCommands){
			if(command == null) return;
			for(CommandExecutor commandExecutor: serverCommandExecutors.values()){
				resendCommands.index(commandExecutor.getServerType(), command);
			}
		}
		
	}

	@Override
	public void start() {
		stop();
		TimeUnit unit = TimeUnit.SECONDS;
		scheduledFuture = SchedulerAssistant.getScheduler().scheduleAtFixedRate(command, clusterEveryseconds, clusterEveryseconds, unit);		
	}

}
