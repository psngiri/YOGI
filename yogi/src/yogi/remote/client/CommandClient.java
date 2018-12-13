package yogi.remote.client;

import java.lang.reflect.Constructor;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;
import yogi.base.stats.Collector;
import yogi.base.stats.StatsCollector;
import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;
import yogi.remote.client.loadbalancer.LoadBalancer;

public class CommandClient {
	public static String 	ServerLookupName 	= "CommandServer";
	public static int 		RegistryPort 		= 9998;	
	public List<String> commandServerAddressesColonPortNumbers;
	public static int 		NumberOfRetryAttempts = 0;
	public static int 		SleepTimeBetweenRetries = 60000; // one minnute
	public static String LoadBalancerClassName="yogi.remote.client.loadbalancer.FailOverLoadBalancer";
	private LoadBalancer loadBalancer;
	
	public CommandClient() {
		super();
		commandServerAddressesColonPortNumbers = new ArrayList<String>();
		setupLoadBalancer(LoadBalancerClassName);
	}
	
	public void setupLoadBalancer(String loadBalancerClassName) {
		if(loadBalancerClassName == null) loadBalancerClassName = LoadBalancerClassName;
		try {
			Class<?> reducerClass = Class.forName(loadBalancerClassName);
			Class<?>[] parameterTypes = new Class[] {};
			Constructor<?> constructor = reducerClass.getConstructor(parameterTypes);
			loadBalancer = (LoadBalancer) constructor.newInstance(new Object[] {});
			loadBalancer.setCommandClient(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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

	public <R extends Object> R execute(Command<R> command) throws CommandException
	{
		String message = "Executing command " + command.getClass().getName() +" with ID " +  command.getID();
		ErrorReporter.get().info(message);
		int retryAttempts = -1;
		while(true)
		{
			try {
				retryAttempts++;
				return executeCommand(command);
			} catch (CommandException e) {
				if(retryAttempts == NumberOfRetryAttempts) throw new CommandException("Error:" + message, e);
				try {
					ErrorReporter.get().info("Sleeping for " + SleepTimeBetweenRetries + " milliSeconds before retrying executeCommand " + retryAttempts + " time");
					Thread.sleep(SleepTimeBetweenRetries);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public <R extends Object> R executeCommand(Command<R> command) throws CommandException{
		Collector timer = new StatsCollector(String.format("Executing command %s ID %s", command.getClass().getSimpleName(), command.getID())).start();
		Pair<String, CommandServer> serverPair = getCommandServer();
		CommandServer server = serverPair.getSecond();
		try {
			return server.execute(command);
		}catch (NoSuchObjectException e) {
			this.setServerNotLive(serverPair);
			ErrorReporter.get().info("Retrying: "+ e.getMessage());
			return executeCommand(command);
		}catch (RemoteException e) {
			try {
				server.isServerAlive();
			} catch (RemoteException e1) {
				this.setServerNotLive(serverPair);
			}
			throw new CommandException("Error: accessing server", e);
		}finally
		{
			timer.stop();
		}
	}
	
	protected Pair<String, CommandServer> getCommandServer() throws  CommandException {
		return loadBalancer.getCommandServer();
	}
	public void setServerNotLive(Pair<String, CommandServer> serverPair){
		loadBalancer.setServerNotLive(serverPair);
	}
	
	public CommandServer getCommandServer(String ipAddress, int port) throws Exception {
		Registry registry = LocateRegistry.getRegistry(ipAddress, port);
		CommandServer server =  (CommandServer)registry.lookup(ServerLookupName);
		if ( server.isServerAlive()) {
			return server;
		}
		throw new CommandException( ipAddress + ":" + port + " Server not alive");
	}
}
