package yogi.remote.client.app;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Processor;
import yogi.remote.CommandException;
import yogi.remote.client.CommandClient;
import yogi.remote.client.pull.CommandClientPull;
import yogi.remote.client.push.CommandClientPush;

public class CommandClientProcessor<R> implements Processor {
	public static boolean RUN = true;
	public static Boolean UsePull = false;
	private Command<R> command;
	private R commandResult;
	private CommandClient commandClient;
	
	public CommandClientProcessor() {
		super();
	}

	public boolean isActivated() {
		return RUN;
	}

	public void run() {
		try {
			commandResult = getCommandClient().execute(command);
		} catch (CommandException e) {
			ErrorReporter.get().error("Error in Command Execution", e.getMessage(), e);
		}
	}

	public CommandClient getCommandClient() {
		if(commandClient == null)
		{
			if(UsePull) commandClient = new CommandClientPull();
			else commandClient = new CommandClientPush();
		}
		return commandClient;
	}

	public R getCommandResult() {
		return commandResult;
	}

	public void setCommand(Command<R> command) {
		this.command = command;
	}
}
