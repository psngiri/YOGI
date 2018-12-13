package yogi.base.util.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.ErrorReporter;

public class Logging {
	public static Logger getLogger(Class<?> klass)
	{
		return Logger.getLogger(klass.getName());
	}
	/**
	 * This method can be used to set the logging level using properties
	 * The way to use is as follows
	 * yogi.base.util.logging.Logging;setLevel=yogi.core.emogt.EmogtCalculator=SEVERE
	 * 
	 * or using a property Alias the level can be set as follows
	 * SetLoggingLevel=yogi.core.emogt.EmogtCalculator=SEVERE
	 * 
	 * @param record - record is expected to be in the following format loggerName=levelName
	 */
	public static void setLevel(String record)
	{
		String[] s2 = record.split("=", 2);
		if(s2.length != 2) ErrorReporter.get().error("= ambiguosly placed", record);
		String loggerName = s2[0].trim();
		String levelName = s2[1].trim().toUpperCase();
		Logger logger = Logger.getLogger(loggerName);
		Level level = Level.parse(levelName);
		logger.setLevel(level);
	}
}
