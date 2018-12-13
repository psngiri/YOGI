package yogi.remote.server.push;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;
import yogi.base.app.error.ErrorReporterListener;
import yogi.remote.client.push.LogRecordListener;


public class ErrorReporterLogRecordListener implements ErrorReporterListener {
	private LogRecordListenerErrorsFormatter formatter;

	public ErrorReporterLogRecordListener(LogRecordListener logRecordListener) {
		super();
		formatter = new LogRecordListenerErrorsFormatter(logRecordListener);
	}

	public LogRecordListenerErrorsFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(LogRecordListenerErrorsFormatter formatter) {
		this.formatter = formatter;
	}

	public void errorReporterPushedToStack(ErrorReporter errorReporter) {
	}

	public void errorReporterPoppedFromStack(ErrorReporter errorReporter) {
		formatter.report(errorReporter);
	}

	public void message(ErrorReporter errorReporter, Level level, Object message, Throwable e) {
		formatter.report(level, message, e);
		if(level == Level.SEVERE) throw new RuntimeException(message.toString(), e);
	}
}
