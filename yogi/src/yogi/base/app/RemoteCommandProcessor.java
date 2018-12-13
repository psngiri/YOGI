package yogi.base.app;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import yogi.base.util.logging.Logging;
import yogi.remote.client.app.MultiServerCommandExecutor;

public abstract class RemoteCommandProcessor extends BaseProcessor {
	private String[] command;
	private Logger logger = Logging.getLogger(RemoteCommandProcessor.class);
	public static String ServerTypeName = "RemoteCommandServer";
	public static Level level = Level.SEVERE;
		
	public void setCommand(String... command)
	{
		this.command = command;
	}
	
	@Override
	public void run() {
		if (command != null) {
			try {
				MultiServerCommandExecutor.get().execute(ServerTypeName, new RemoteCommandProcessorCommand(command));
			} catch (Throwable e) {
				logger.log(level, "Error in Executing Command "+Arrays.toString(command), e);
				if (ErrorReporter.DumpErrorStack) {
	                StringWriter sw = new StringWriter();
	                e.printStackTrace(new PrintWriter(sw));
	                ErrorReporter.get().warning(sw.toString());
				}
			}
		}
	}


}
