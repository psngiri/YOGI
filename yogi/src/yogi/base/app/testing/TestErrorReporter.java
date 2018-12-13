package yogi.base.app.testing;

import java.util.logging.Level;

import yogi.base.app.ErrorReporter;
import yogi.base.app.ErrorReporterExtention;
import yogi.base.app.error.ErrorReporterListener;
import yogi.base.app.error.ErrorsLoggerFormatter;

public class TestErrorReporter {
	
	public static void start()
	{
		ErrorReporter errorReporter = new ErrorReporter();
		errorReporter.addErrorReporterListener(new MyErrorReporterListener());
		ErrorReporterExtention.push(errorReporter);
	}
	
	public static void end()
	{
		ErrorReporterExtention.pop();
	}
	
	static class MyErrorReporterListener implements ErrorReporterListener
	{
		ErrorsLoggerFormatter formatter = new ErrorsLoggerFormatter();

		public void errorReporterPushedToStack(ErrorReporter errorReporter) {
		}

		public void errorReporterPoppedFromStack(ErrorReporter errorReporter) {
			formatter.report(errorReporter);
		}

		public void message(ErrorReporter reporter, Level level, Object message, Throwable e) {
			String base = "Base";
			if(level == Level.SEVERE)
			{
				reporter.error(base, message, e);
				throw new RuntimeException(message.toString(), e);
			}else if(level == Level.WARNING)
			{
				reporter.warning(base, message);
			}else if(level == Level.INFO)
			{
				reporter.info(base, message);
			}else if(level == Level.FINE)
			{
				reporter.fine(base, message);
			}else if(level == Level.FINER)
			{
				reporter.finer(base, message);
			}else if(level == Level.FINEST)
			{
				reporter.finest(base, message);
			}
			
		}
	}
}
