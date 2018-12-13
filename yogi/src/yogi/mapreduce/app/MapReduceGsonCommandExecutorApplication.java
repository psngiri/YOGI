package yogi.mapreduce.app;

import yogi.mapreduce.MapReduceGsonCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;


public class MapReduceGsonCommandExecutorApplication {
	public static void main(String[] args) {
		if(args.length < 3) {
			System.out.println("Usage: MapReduceGsonCommandApplication  commandServerAddressesColonPortNumbers commandName value [userId]");
			return;
		}
		String commandServerAddressesColonPortNumbers = args[0];
		String commandName = args[1];
		String value = args[2];
		String userId = null;
		if(args.length > 3) userId = args[3];
		MapReduceGsonCommandExecutorApplication gsonCommandApplication = new MapReduceGsonCommandExecutorApplication();
		gsonCommandApplication.execute(commandServerAddressesColonPortNumbers, commandName, value, userId);
		System.exit(0);
	}

	private void execute(String commandServerAddressesColonPortNumbers, String commandName, String value, String UserId) {
		MapReduceGsonCommand gsonCommand = new MapReduceGsonCommand(UserId, commandName, value);
		CommandExecutor commandExecutor = new CommandExecutor("",commandServerAddressesColonPortNumbers);
		try {
			String rtnValue = commandExecutor.execute(gsonCommand);
			System.out.println(rtnValue);
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}

}
