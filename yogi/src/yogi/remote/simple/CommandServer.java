package yogi.remote.simple;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CommandServer extends Remote {
	Object execute(Command command) throws RemoteException;
	boolean isServerAlive() throws RemoteException;
}
