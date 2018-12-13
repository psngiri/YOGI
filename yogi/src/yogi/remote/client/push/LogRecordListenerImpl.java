package yogi.remote.client.push;

import java.rmi.RemoteException;

import yogi.base.app.ErrorReporter;
import yogi.remote.UnicastRemoteObject;
import yogi.remote.log.LogRecord;


public class LogRecordListenerImpl extends UnicastRemoteObject implements LogRecordListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorReporter errorReporter;

	public LogRecordListenerImpl(ErrorReporter errorReporter) throws RemoteException {
		super();
		this.errorReporter = errorReporter;
	}


	public void report(LogRecord logRecord) throws RemoteException {
		errorReporter.log(logRecord.getLevel(), logRecord.getMessage(), logRecord.getThrowable());
	}

}
