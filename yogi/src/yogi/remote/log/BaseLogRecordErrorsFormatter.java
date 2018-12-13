package yogi.remote.log;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.error.ErrorsFormatter;
import yogi.base.util.logging.Logging;
import yogi.remote.server.ServerErrorsFormatter;

public abstract class BaseLogRecordErrorsFormatter extends ErrorsFormatter
{
	private static Logger logger = Logging.getLogger(ServerErrorsFormatter.class);
	
	public BaseLogRecordErrorsFormatter() {
		super();
	}

	public void report(Level level, Map.Entry<String, List<Object>> message) {
		if (logger.isLoggable(level))
		{
			String convertToString = convertToString(message);
			log(new LogRecord(level, convertToString, null));
		}
	}
	
	public void report(Level level, Map.Entry<String, List<Object>> message, Throwable throwable) {
		if (logger.isLoggable(level))
		{
			log(new LogRecord(level, convertToString(message), throwable));
		}
	}

	public void report(Level level, Object message, Throwable e) {
		if (logger.isLoggable(level))
		{
			log(new LogRecord(level, convertToString(message), e));
		}
	}

	public abstract  void log(LogRecord logRecord);
	
	@Override
	public String convertToString(Entry<String, List<Object>> message) {
		return appendThreadInformaton(super.convertToString(message));
	}

	private String appendThreadInformaton(String message) {
		long threadId = Thread.currentThread().getId();
		StringBuilder sb = new StringBuilder();
		sb.append("Thread(").append(threadId).append(")");
		return sb.toString() + message;
	}

	@Override
	public String convertToString(Object message) {
		return appendThreadInformaton(super.convertToString(message));
	}
}
