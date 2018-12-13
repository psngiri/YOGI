package yogi.base.app.error;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;


public interface ErrorReporterListener {
	void errorReporterPushedToStack(ErrorReporter errorReporter);
	void errorReporterPoppedFromStack(ErrorReporter errorReporter);
	void message(ErrorReporter reporter, Level level, Object message, Throwable e);
}
