package yogi.base.network.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.network.command.RemoveFromNetworkCommand;
import yogi.base.network.command.ServersInNetworkCommand;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;

public class ServerCommandExecutorManagerTestServer extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}
	
	public void testServersInNetworkCommand() throws CommandException{
		String serverAddress = "localhost:6080";
		CommandExecutor commandExecutor = new CommandExecutor(serverAddress, serverAddress);
		ServersInNetworkCommand command = new ServersInNetworkCommand();
		List<String> serversInNetwork = commandExecutor.execute(command);
		System.out.println(serversInNetwork);
	}
		
	public void testRemoveFromNetworkCommand() throws CommandException{
		String serverAddress = "localhost:6080";
		CommandExecutor commandExecutor = new CommandExecutor(serverAddress, serverAddress);
		RemoveFromNetworkCommand command = new RemoveFromNetworkCommand(serverAddress);
		Boolean rtnValue = commandExecutor.execute(command);
		System.out.println(rtnValue);
	}
}
