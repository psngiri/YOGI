package yogi.remote.client.push;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;
import yogi.base.stats.Collector;
import yogi.base.stats.StatsCollector;
import yogi.base.util.Pair;
import yogi.remote.CommandException;
import yogi.remote.CommandServer;
import yogi.remote.client.CommandClient;

public class CommandClientPush extends CommandClient{
	public CommandClientPush() {
		super();
	}


	public <R extends Object> R executeCommand(Command<R> command) throws CommandException
	{
		Collector timer = new StatsCollector(String.format("Executing command %s ID %s", command.getClass().getSimpleName(), command.getID())).start();
		Pair<String, CommandServer> serverPair = getCommandServer();
		CommandServer server = serverPair.getSecond();
		try {
			return server.execute(command, new LogRecordListenerImpl(ErrorReporter.get()));
		}catch (NoSuchObjectException e) {
			this.setServerNotLive(serverPair);
			ErrorReporter.get().info("Retrying: "+ e.getMessage());
			return executeCommand(command);
		} catch (RemoteException e) {
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
	
}
