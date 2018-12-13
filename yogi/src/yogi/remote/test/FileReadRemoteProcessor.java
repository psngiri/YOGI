package yogi.remote.test;

import yogi.base.app.BaseProcessor;
import yogi.base.app.ErrorReporter;
import yogi.base.app.Executor;
import yogi.remote.client.app.CommandClientProcessor;

public class FileReadRemoteProcessor extends BaseProcessor{
	public static boolean RUN = true;
	public static String commandServerAddressesColonPortNumbers = "cpappd02.corp.amrcorp.com:1099;XXXX:1900;Q0513984.corpaa.aa.com";
	private String id;

	public FileReadRemoteProcessor(String id) {
		super();
		this.id = id;
	}

	public void run() {
		CommandClientProcessor<String> commandClientProcessor = new CommandClientProcessor<String>();
		commandClientProcessor.getCommandClient().addCommandServerAddressesColonPortNumbers(commandServerAddressesColonPortNumbers);
		FileReadCommand command = new FileReadCommand("/cphome/549915/giri/commandServer.sh", 3, id);
		commandClientProcessor.setCommand(command);
		Executor.get().execute(commandClientProcessor);
		String messagesSent = commandClientProcessor.getCommandResult();
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
		FileReadRemoteProcessor testRemoteProcessor = new FileReadRemoteProcessor(args[0]);
		Executor.get().execute(testRemoteProcessor);
//		System.exit(0);
	}
}
