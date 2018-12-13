package yogi.remote.client.app;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import yogi.base.app.Executor;
import yogi.base.app.Processor;
import yogi.base.app.testing.TestModule;
import yogi.remote.CommandException;


public class MultiServerCommandExecutor{
	public static final String Separator2 = "=";
	public static final String Separator1 = ",";
	public static HashSet<String> ServerType = new HashSet<String>();
	public static String DefaultServer = "DefaultServer";
	private static Map<String, CommandExecutor> serverCommandExecutors = new HashMap<String, CommandExecutor>();
	private static Map<String, String> serverLoadBalancerClassNames = new HashMap<String, String>();
	private static MultiServerCommandExecutor executor = new MultiServerCommandExecutor();
	//  "Domestic=XXXX:1900;Q0513937.corpaa.aa.com,International=XXXX:1900;Q0513937.corpaa.aa.com"
	public static void setServerTypeEqualsCommandServerAddressesColonPortNumbers(String serverTypeEqualsCommandServerAddressesColonPortNumbers)
	{
		serverCommandExecutors.clear();
		addServerTypeEqualsCommandServerAddressesColonPortNumbers(serverTypeEqualsCommandServerAddressesColonPortNumbers);
	}

	public static void addServerTypeEqualsCommandServerAddressesColonPortNumbers(String serverTypeEqualsCommandServerAddressesColonPortNumbers) {
		String[] splits = serverTypeEqualsCommandServerAddressesColonPortNumbers.split(Separator1);
		for(String split: splits)
		{
			String[] splits2 = split.split(Separator2);
			if(splits2.length < 2) continue;
			serverCommandExecutors.put(splits2[0].trim(), new CommandExecutor(splits2[0], splits2[1].trim(), serverLoadBalancerClassNames.get(splits2[0])));
			if(serverCommandExecutors.get(DefaultServer) ==null){
				serverCommandExecutors.put(DefaultServer, new CommandExecutor(splits2[0], splits2[1].trim(), serverLoadBalancerClassNames.get(splits2[0])));
			}
		}
	}
	//"serverType1=yogi.remote.client.loadbalancer.FailOverLoadBalancer,serverType2=yogi.remote.client.loadbalancer.FailOverLoadBalancer"
	public static void addServerLoadBalancerClassNames(String serverTypeLoadBalancerClassNames)
	{
		String[] splits = serverTypeLoadBalancerClassNames.split(Separator1);
		for(String split: splits)
		{
			String[] splits2 = split.split(Separator2);
			if(splits2.length < 2) continue;
			String value = splits2[1].trim();
			if(value.isEmpty()) value = null;
			serverLoadBalancerClassNames.put(splits2[0], value);
		}
	}
	
	protected MultiServerCommandExecutor() {
		super();
		setServerTypeEqualsCommandServerAddressesColonPortNumbers("Default=XXXX:1900");
	}

	public static MultiServerCommandExecutor get() {
		return executor;
	}

	public <R> R execute(String serverType, CommandAdapter<R> command) throws CommandException
	{
		if(serverType == null || serverType.isEmpty()) return execute(command);
		if(ServerType.contains(serverType)) return Executor.get().execute(command);
		CommandExecutor serverCommandExecutor = serverCommandExecutors.get(serverType);
		return serverCommandExecutor.execute(command);
	}
	
	public <R> R execute(CommandAdapter<R> command)  throws CommandException
	{
		return execute(DefaultServer, command);
	}
	
	public void initialize(){
		if(!BaseCommandExecutor.Initialized){
			execute(new TestModule());
		}
	}
	
	public void execute(Processor processor)
	{
		Collection<CommandExecutor> commandExecutors = serverCommandExecutors.values();
		if(commandExecutors.isEmpty()) throw new RuntimeException("No Servers Defined, please set the property setserverTypeEqualsCommandServerAddressesColonPortNumbers");
		commandExecutors.iterator().next().getExecutor().execute(processor);
	}
}
