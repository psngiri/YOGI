package yogi.base.app.error;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;


public class ErrorReporterLogListener implements ErrorReporterListener {
	ErrorsLoggerFormatter formatter = new ErrorsLoggerFormatter();
	public ErrorsLoggerFormatter getFormatter() {
		return formatter;
	}

	public void setFormatter(ErrorsLoggerFormatter formatter) {
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
