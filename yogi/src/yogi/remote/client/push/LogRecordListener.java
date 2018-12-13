package yogi.remote.client.push;

import java.rmi.Remote;
import java.rmi.RemoteException;

import yogi.remote.log.LogRecord;



public interface LogRecordListener extends Remote{
	void report(LogRecord logRecord) throws RemoteException;
}
