package yogi.base.app;

import yogi.remote.client.app.CommandAdapter;

public class RemoteCommandProcessorCommand extends CommandAdapter<Boolean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9121610476499022697L;
	private String[] commandString;
	
	public RemoteCommandProcessorCommand(String[] commandString) {
		super(null);
		this.commandString = commandString;
	}

	@Override
	public Boolean execute() {
		CommandProcessor commandProcessor = new CommandProcessor();
		commandProcessor.setCommand(commandString);
		Executor.get().execute(commandProcessor);
		return true;
	}

}
