package yogi.base.io.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteReader extends Remote {
	void close() throws RemoteException;
	char[] readRemote(int len) throws RemoteException;
}
