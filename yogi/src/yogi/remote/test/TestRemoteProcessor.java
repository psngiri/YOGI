package yogi.remote.test;

import java.util.logging.Level;

import yogi.base.app.BaseProcessor;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.remote.client.app.CommandClientProcessor;

public class TestRemoteProcessor extends BaseProcessor{
	public static boolean RUN = true;
	public static String commandServerAddressesColonPortNumbers = "XXXX:1900;Q0513984.corpaa.aa.com";
	private String id;

	public TestRemoteProcessor(String id) {
		super();
		this.id = id;
	}

	public void run() {
		CommandClientProcessor<Integer> commandClientProcessor = new CommandClientProcessor<Integer>();
		commandClientProcessor.getCommandClient().addCommandServerAddressesColonPortNumbers(commandServerAddressesColonPortNumbers);
		TestCommand command = new TestCommand(10, Level.INFO, id);
		commandClientProcessor.setCommand(command);
		Executor.get().execute(commandClientProcessor);
		Integer messagesSent = commandClientProcessor.getCommandResult();
		ErrorReporter.get().info(String.format("Sent %s Messages", messagesSent));
	}

	public boolean isActivated() {
		return RUN;
	}
	
	public static void main(String[] args) {
		if(args.length < 1) {
			System.out.println("Usage: TestRemoteProcessor id");
			return;
		}
		TestRemoteProcessor testRemoteProcessor = new TestRemoteProcessor(args[0]);
		Executor.get().execute(testRemoteProcessor);
//		System.exit(0);
	}
}
