package yogi.remote.server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import yogi.base.app.Command;
import yogi.base.app.ErrorReporter;
import yogi.base.app.ErrorReporterExtention;
import yogi.base.app.Executor;
import yogi.base.app.error.ErrorReporterLogListener;
import yogi.remote.CommandServer;
import yogi.remote.UnicastRemoteObject;
import yogi.remote.client.push.LogRecordListener;
import yogi.remote.log.LogRecord;
import yogi.remote.server.push.ErrorReporterLogRecordListener;

public class CommandServerImpl extends UnicastRemoteObject implements CommandServer {

	/**
	 *
	 */
	private static final long serialVersionUID = 3746451174455824795L;
	private static boolean isServerAlive = true;

	public CommandServerImpl() throws RemoteException {
		super();
	}

	public boolean isServerAlive() throws RemoteException {
		return isServerAlive;
	}

	public <R> R execute(Command<R> command, LogRecordListener logRecordListener) throws RemoteException {

		ErrorReporter errorReporter = startRecording();
		errorReporter.addErrorReporterListener(new ErrorReporterLogRecordListener(logRecordListener));
		return executeCommand(command, errorReporter);
	}

	private <R> R executeCommand(Command<R> command, ErrorReporter errorReporter)	throws RemoteException {
		R rtnValue;
		try {
			if(!isServerAlive) throw new RemoteException("Server is Offline");
			rtnValue = Executor.get().execute(command);
		} catch (Throwable e) {
			throw new RemoteException(e.getMessage(), e);
		}finally
		{
			endRecording(errorReporter);
		}
		return rtnValue;
	}

	public <R> R execute(Command<R> command) throws RemoteException {

		ErrorReporter errorReporter = startRecording();
		errorReporter.addErrorReporterListener(ErrorReporterServerListener.get(command.getID()));
		return executeCommand(command, errorReporter);
	}

	private void endRecording(ErrorReporter errorReporter) {
		while(!ErrorReporterExtention.isEmpty() && ErrorReporterExtention.pop() != errorReporter){}
	}

	private ErrorReporter startRecording() {
		ErrorReporter errorReporter = new ErrorReporter();
		ErrorReporterExtention.push(errorReporter);
		errorReporter.addErrorReporterListener(new ErrorReporterLogListener());
		return errorReporter;
	}

	public ArrayList<LogRecord> getMessages(String id) throws RemoteException
	{
		ErrorReporter errorReporter = startRecording();
		try {
			return ErrorReporterServerListener.emptyLogRecords(id);
		} catch (Throwable e) {
			throw new RemoteException(e.getMessage(), e);
		}finally
		{
			endRecording(errorReporter);
		}
	}

	public static void setServerAlive(String isServerAlive) {
		setServerAlive(Boolean.valueOf(isServerAlive));
	}
	
	public static void setServerAlive(boolean isServerAlive) {
		if (isServerAlive) {
			ErrorReporter.get().info("Bringing Server Online");
		} else {
			ErrorReporter.get().info("Taking Server Offline");
		}
		CommandServerImpl.isServerAlive = isServerAlive;
	}

	public static boolean isServerAvailable() {
		return isServerAlive;
	}
	
}
