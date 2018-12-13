package yogi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import yogi.base.app.Command;
import yogi.remote.client.push.LogRecordListener;
import yogi.remote.log.LogRecord;


public interface CommandServer extends Remote {
	<R> R execute(Command<R> command, LogRecordListener logRecordListener) throws RemoteException;
	<R> R execute(Command<R> command) throws RemoteException;
	boolean isServerAlive() throws RemoteException;
	ArrayList<LogRecord> getMessages(String id) throws RemoteException;	
}
