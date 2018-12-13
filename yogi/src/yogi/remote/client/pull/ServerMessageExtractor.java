package yogi.remote.client.pull;

import java.rmi.RemoteException;
import java.util.ArrayList;

import yogi.base.app.ErrorReporter;
import yogi.base.app.ErrorReporterExtention;
import yogi.remote.CommandServer;
import yogi.remote.log.LogRecord;

class ServerMessageExtractor implements Runnable {
	public static int WaitTimeInMilliSeconds = 1000;
	private boolean stop = false;
	private CommandServer commandServer;
	private String id;
	private ErrorReporter errorReporter;
	
	public ServerMessageExtractor(CommandServer commandServer, String id, ErrorReporter errorReporter) {
		super();
		this.commandServer = commandServer;
		this.id = id;
		this.errorReporter = errorReporter;
	}

	public void run() {
		ErrorReporterExtention.push(errorReporter);
		while(true)
		{
			try {
				getMessagesAndReport();
				if(stop)
				{
					getMessagesAndReport();
					break;
				}
				Thread.sleep(WaitTimeInMilliSeconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			} catch (RemoteException e) {
				e.printStackTrace();
				break;
			}
		}
		ErrorReporterExtention.pop();
	}

	private void getMessagesAndReport() throws RemoteException {
		ArrayList<LogRecord> messages = commandServer.getMessages(id);
		for(LogRecord logRecord: messages)
		{
			ErrorReporter.get().log(logRecord.getLevel(), logRecord.getMessage(), logRecord.getThrowable());
		}
	}

	public void start()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void stop()
	{
		stop = true;
	}
}
