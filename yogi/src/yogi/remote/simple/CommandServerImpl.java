package yogi.remote.simple;

import java.rmi.RemoteException;
import java.util.logging.Logger;



public class CommandServerImpl extends UnicastRemoteObject implements CommandServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746451174455824795L;
	private static Logger logger = Logger.getLogger(CommandServerImpl.class.getName());

	
	public CommandServerImpl() throws RemoteException {
		super();
	}
	
	public boolean isServerAlive() throws RemoteException {
		return true;
	}

	public Object execute(Command command) throws RemoteException {
		
		Object rtnValue;
		try {
			logger.info(String.format("Executing command %s with ID %s ", command.getClass().getName(), command.getID()));
			rtnValue = command.execute();
		} catch (RuntimeException e) {
			throw new RemoteException(e.getMessage(), e);
		}
		return rtnValue;
	}
}
