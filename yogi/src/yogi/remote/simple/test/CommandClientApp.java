package yogi.remote.simple.test;

import java.util.logging.Logger;

import yogi.remote.simple.CommandClient;
import yogi.remote.simple.CommandException;


public class CommandClientApp  {
	private static Logger logger = Logger.getLogger(CommandClientApp.class.getName());

	public static void main(String[] args) throws InterruptedException {
		if(args.length < 1) {
			System.out.println("Usage: CommandClientApp commandServerAddressColonPortNumbersSeparatedBySemiColons");
			return;
		}
		CommandClient client = new CommandClient();
		client.addCommandServerAddressesColonPortNumbers(args[0]);
		try {
			String returnValue = (String) client.execute(new FileReadCommand("/cphome/549915/giri/commandServer.sh", 3, "GiriTest"));
			logger.info(returnValue);
		} catch (CommandException e) {
			e.printStackTrace();
		}
//		while(true)
//		{
//			try {
//				String returnValue = (String) client.execute(new FileReadCommand("/cphome/549915/giri/commandServer.sh", 3, "GiriTest"));
//				logger.info(returnValue);
//				break;
//			} catch (CommandException e) {
//				e.printStackTrace();
//				logger.info("Sleeping for 5 seconds");
//				Thread.sleep(5000);
//				logger.info("Woke up from sleep");
//			}
//		}
	}
}
