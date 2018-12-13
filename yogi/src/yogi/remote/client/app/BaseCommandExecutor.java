package yogi.remote.client.app;

import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.base.stats.Collector;
import yogi.base.stats.StatsCollector;
import yogi.remote.CommandException;
import yogi.remote.client.CommandClient;
import yogi.remote.client.pull.CommandClientPull;
import yogi.remote.client.push.CommandClientPush;

public abstract class BaseCommandExecutor {
	public static Boolean UsePull;
	public static boolean Initialized = false;
	private static MyExecutor executor = new MyExecutor();
	private int id = 0;
	private CommandClient commandClient;

	public Executor getExecutor(){
		return executor;
	}
	
	public <R> R execute(CommandAdapter<R> command) throws CommandException
	{
		executor.startExecutionRecording();
		Collector timer = new StatsCollector("Executing Command: " + command.getClass().getSimpleName()).start();
		try {
			command.setId(getId());
			return getCommandClient().execute(command);
		} catch (CommandException e) {
			ErrorReporter.get().warning("Error in Command Execution",e);
			throw e;
		}finally{
			timer.stop();
			executor.endExecutionRecording();			
		}
	}
	
	private String getId() {
		if(id == Integer.MAX_VALUE) id = resetId();
		return getName() + id++;
	}

	protected abstract String getName();

	private synchronized int resetId() {
		if(id == Integer.MAX_VALUE) return 0;
		return id++;
	}

	private CommandClient getCommandClient() {
		if(commandClient == null)
		{
			Boolean commandClientType = getUsePull();
			if(commandClientType == null)commandClient = new CommandClient();
			else if(commandClientType) commandClient = new CommandClientPull();
			else commandClient = new CommandClientPush();
			commandClient.addCommandServerAddressesColonPortNumbers(getCommandServerAddressesColonPortNumbers());
			commandClient.setupLoadBalancer(getLoadBalancerClassName());
		}
		return commandClient;
	}

	public String getLoadBalancerClassName() {
		return null;
	}

	protected Boolean getUsePull() {
		return UsePull;
	}

	protected abstract String getCommandServerAddressesColonPortNumbers();

	static class MyExecutor extends Executor
	{
		@Override
		public void endExecutionRecording() {
			super.endExecutionRecording();
		}

		@Override
		public void startExecutionRecording() {
			initialize();
			super.startExecutionRecording();
		}

		@Override
		public void startExecutionRecording(ErrorReporter errorReporter) {
			super.startExecutionRecording(errorReporter);
		}

		protected void initialize() {
			if(!Initialized){
				Initialized = true;
				readLoggingPropertiesFile();
				readPropertyAliases();
				loadPropertyFile("*/config/commonProperties.dat");
				loadPropertyFile("*/config/properties.dat");
			}
		}
		
	}
}
