package yogi.remote.forward.test;

import junit.framework.TestCase;

import yogi.base.app.ErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandAdapter;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.remote.forward.ForwardCommand;
import yogi.remote.gson.GsonCommand;

public class ForwardCommandTestServer extends TestCase {

	public void test(){
		MultiServerCommandExecutor.setServerTypeEqualsCommandServerAddressesColonPortNumbers("Test1=localhost:6080,Test2=localhost:6081");
		BaseCommandExecutor.Initialized = true;
		String className = MyTestCommand.class.getName();
		GsonCommand command = new GsonCommand(className, JsonAssistant.get().toJson(new MyTestCommand("AA549915", "Executed MyTestCommand1")), "AA549915");
		GsonCommand command1 = new GsonCommand(className, JsonAssistant.get().toJson(new MyTestCommand("AA549915", "Executed MyTestCommand2")), "AA549915");
		GsonCommand command2 = new GsonCommand(className, JsonAssistant.get().toJson(new MyTestCommand("AA549915", "Executed MyTestCommand3")), "AA549915");
		try {
			MultiServerCommandExecutor.get().execute("Test1", new ForwardCommand("Test1", command));
			MultiServerCommandExecutor.get().execute("Test2", new ForwardCommand("Test2", command1));
			MultiServerCommandExecutor.get().execute("Test2", command2);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
	public static class MyTestCommand extends CommandAdapter<String>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String message;
		public MyTestCommand(String userId, String message) {
			super(userId);
			this.message = message;
		}

		@Override
		public String execute() {
			ErrorReporter.get().info(message);
			return message;
		}
		
	}
}
