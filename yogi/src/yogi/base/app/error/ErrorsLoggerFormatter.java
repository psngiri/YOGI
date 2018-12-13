package yogi.base.app.error;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.util.logging.Logging;

public class ErrorsLoggerFormatter extends ErrorsFormatter
{
	private static Logger logger = Logging.getLogger(ErrorsLoggerFormatter.class);
	
	public ErrorsLoggerFormatter() {
		super();
	}

	public void report(Level level, Map.Entry<String, List<Object>> message) {
		if (logger.isLoggable(level))
		{
			String convertToString = convertToString(message);
			logger.log(level, convertToString);
		}
	}
	
	public void report(Level level, Map.Entry<String, List<Object>> message, Throwable throwable) {
		if (logger.isLoggable(level))
		{
			logger.log(level, convertToString(message));
			if(throwable != null) logger.log(level, message.getKey(), throwable);
		}
	}

	public void report(Level level, Object message, Throwable e) {
		if (logger.isLoggable(level))
		{
			if(e == null) logger.log(level, convertToString(message));
			else logger.log(level, convertToString(message), e);
		}
	}
}
