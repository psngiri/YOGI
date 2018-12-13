package yogi.remote.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import yogi.base.app.ErrorReporter;
import yogi.base.app.error.ErrorReporterListener;
import yogi.remote.log.LogRecord;


public class ErrorReporterServerListener implements ErrorReporterListener {
	private ServerErrorsFormatter formatter = new ServerErrorsFormatter();
	private boolean popped = false;
	private static HashMap<String, ErrorReporterServerListener> errorReporterServerListeners = new HashMap<String, ErrorReporterServerListener>();

	public ServerErrorsFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(ServerErrorsFormatter formatter) {
		this.formatter = formatter;
	}

	public void errorReporterPushedToStack(ErrorReporter errorReporter) {
	}

	public void errorReporterPoppedFromStack(ErrorReporter errorReporter) {
		formatter.report(errorReporter);
		popped = true;
	}

	public void message(ErrorReporter errorReporter, Level level, Object message, Throwable e) {
		formatter.report(level, message, e);
		if(level == Level.SEVERE) throw new RuntimeException(message.toString(), e);
	}

	
	public boolean isPopped() {
		return popped;
	}

	public static ErrorReporterServerListener get(String id)
	{
		ErrorReporterServerListener errorReporterServerListener = new ErrorReporterServerListener();
		errorReporterServerListeners.put(id, errorReporterServerListener);
		return errorReporterServerListener;
	}
	
	public static ArrayList<LogRecord> emptyLogRecords(String id)
	{
		ErrorReporterServerListener errorReporterServerListener = errorReporterServerListeners.get(id);
		if(errorReporterServerListener == null) return new ArrayList<LogRecord>(0);
		if(errorReporterServerListener.isPopped())
		{
			errorReporterServerListeners.remove(id);
			ErrorReporter.get().info("Removed Message Listener for Command with id: " + id);
		}
		return errorReporterServerListener.getFormatter().emptyLogRecords();
	}
}
