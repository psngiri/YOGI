package yogi.remote.client.loadbalancer;

import java.util.List;
import java.util.Scanner;

import yogi.base.app.ErrorReporter;
import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;
import yogi.remote.client.CommandClient;

public abstract class LoadBalancer {
	private CommandClient commandClient;
	
	public void setCommandClient(CommandClient commandClient) {
		this.commandClient = commandClient;
	}
	
	protected List<String> getCommandServerAddressesColonPortNumbers()
	{
		return commandClient.commandServerAddressesColonPortNumbers;
	}
	
	protected Pair<String, CommandServer> connect(String ipAddressCollonPortNumber) {
		Pair<String, CommandServer> commandServerPair = null;
		Scanner scanner = new Scanner(ipAddressCollonPortNumber);
		scanner.useDelimiter(":");
		String ipAddress = scanner.next();
		int portNumber = CommandClient.RegistryPort;
		if(scanner.hasNext()) portNumber = Integer.parseInt(scanner.next());
		try {
			ErrorReporter.get().info(String.format("Connecting to Server %s at port %s",ipAddress, portNumber));
			commandServerPair = new Pair<String, CommandServer>(ipAddressCollonPortNumber, getCommandServer(ipAddress, portNumber));
		} catch (Exception e) {
			ErrorReporter.get().warning(e.getMessage());
		}
		return commandServerPair;
	}
	
	private CommandServer getCommandServer(String ipAddress, int port) throws Exception{
		return commandClient.getCommandServer(ipAddress, port);
	}
	
	public abstract Pair<String, CommandServer> getCommandServer() throws  CommandException;
	
	public abstract void setServerNotLive(Pair<String, CommandServer> serverPair);
}
