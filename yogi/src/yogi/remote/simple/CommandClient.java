package yogi.remote.simple;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


public class CommandClient {
	public static String 	ServerLookupName 	= "CommandServer";
	public static int 		RegistryPort 		= 9998;	
	Logger logger = Logger.getLogger(this.getClass().getName());
	public List<String> commandServerAddressesColonPortNumbers;
	private CommandServer commandServer = null;
	
	public CommandClient() {
		super();
		commandServerAddressesColonPortNumbers = new ArrayList<String>();
	}

	public void addCommandServerAddressesColonPortNumbers(String commandServerAddressesColonPortNumbers) {
		Scanner scanner = new Scanner(commandServerAddressesColonPortNumbers);
		scanner.useDelimiter(";");
		while(scanner.hasNext())
		{
			this.commandServerAddressesColonPortNumbers.add(scanner.next());
		}
	}

	public void addCommandServerAddressAndPortNumber(String commandServerAddress, int portNumber) {
		commandServerAddressesColonPortNumbers.add(commandServerAddress + ":" + portNumber);
	}

	public Object execute(Command command) throws CommandException
	{
		String message = String.format("Executing command %s with ID %s ", command.getClass().getName(), command.getID());
		try {
			logger.info(message);
			if(commandServer == null) commandServer = getCommandServer();
			return commandServer.execute(command);
		} catch (RemoteException e) {
			try {
				commandServer.isServerAlive();
			} catch (RemoteException e1) {
				commandServer = null;
			}
			throw new CommandException("Error:" + message, e);
		}
	}
	
	protected CommandServer getCommandServer() throws  CommandException {
		for(String ipAddressCollonPortNumber: commandServerAddressesColonPortNumbers)
		{
			Scanner scanner = new Scanner(ipAddressCollonPortNumber);
			scanner.useDelimiter(":");
			String ipAddress = scanner.next();
			int portNumber = RegistryPort;
			if(scanner.hasNext()) portNumber = Integer.parseInt(scanner.next());
			try {
				logger.info(String.format("Connecting to Server %s at port %s",ipAddress, portNumber));
				return getCommandServer(ipAddress, portNumber);
			} catch (Exception e) {
				logger.warning(e.getMessage());
			}
		}
		throw new CommandException("Could not connect to any of the servers:" + commandServerAddressesColonPortNumbers);
	}
	
	private CommandServer getCommandServer(String ipAddress, int port) throws Exception {
		Registry registry = LocateRegistry.getRegistry(ipAddress, port);
		CommandServer server =  (CommandServer)registry.lookup(ServerLookupName);
		if ( server.isServerAlive()) {
			return server;
		}
		throw new CommandException( ipAddress + ":" + port + " Server not alive");
	}
}
