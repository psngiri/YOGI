package yogi.remote.client.app;

import java.rmi.server.UID;

public class CommandExecutor extends BaseCommandExecutor{
	public static Boolean UsePull;
	private String serverType;
	public static String commandServerAddressesColonPortNumbers = "XXXX:1900;Q0513937.corpaa.aa.com";
	private String myCommandServerAddressesColonPortNumbers;
	private UID uid = new UID(); 
	private String loadBalancerClassName;
	
	private static CommandExecutor commandExecutor = new CommandExecutor();
	
	protected CommandExecutor() {
		super();
	}

	public CommandExecutor(String serverType, String commandServerAddressesColonPortNumbers) {
		this(serverType, commandServerAddressesColonPortNumbers, null);
	}
	
	public CommandExecutor(String serverType, String commandServerAddressesColonPortNumbers, String loadBalancerClassName) {
		super();
		this.serverType = serverType;
		this.myCommandServerAddressesColonPortNumbers = commandServerAddressesColonPortNumbers;
		this.loadBalancerClassName = loadBalancerClassName;
	}
	
	public static CommandExecutor get() {
		return commandExecutor;
	}

	@Override
	protected Boolean getUsePull() {
		return UsePull;
	}

	@Override
	protected String getName() {
		return uid.toString();
	}

	public String getServerType() {
		return serverType;
	}

	@Override
	protected String getCommandServerAddressesColonPortNumbers() {
		if(myCommandServerAddressesColonPortNumbers != null) return myCommandServerAddressesColonPortNumbers;
		return commandServerAddressesColonPortNumbers;
	}

	@Override
	public String getLoadBalancerClassName() {
		return loadBalancerClassName;
	}

}
