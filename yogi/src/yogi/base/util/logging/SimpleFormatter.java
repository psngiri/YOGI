package yogi.base.util.logging;

import java.io.*;
import java.text.*;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;


public class SimpleFormatter extends Formatter {
	public static boolean WriteTimeStamp = true;
	public static boolean WriteClassAndMethodNames = true;
	public static boolean WriteStackTrace = true;
    Date date = new Date();
    private final static String format = "{0,date} {0,time}";
    private MessageFormat formatter;

    private Object args[] = new Object[1];
	private String lineSeparator = System.getProperty("line.separator");

    /**
     * Format the given LogRecord.
     * @param record the log record to be formatted.
     * @return a formatted log record
     */
    public synchronized String format(LogRecord record) {
	StringBuilder sb = new StringBuilder();
	appendTimestamp(record, sb);
	appendClassAndMethodNames(record, sb);
	String message = formatMessage(record);
	sb.append(record.getLevel().getLocalizedName());
	sb.append(": ");
	sb.append(message);
	sb.append(lineSeparator);
	appendStackTrace(record, sb);
	return sb.toString();
    }

	private void appendStackTrace(LogRecord record, StringBuilder sb) {
		if(!WriteStackTrace) return;
		if (record.getThrown() != null) {
		    try {
		        StringWriter sw = new StringWriter();
		        PrintWriter pw = new PrintWriter(sw);
		        record.getThrown().printStackTrace(pw);
		        pw.close();
			sb.append(sw.toString());
		    } catch (Exception ex) {
		    }
		}
	}

	private void appendClassAndMethodNames(LogRecord record, StringBuilder sb) {
		if(WriteClassAndMethodNames)
		{
			if (record.getSourceClassName() != null) {	
			    sb.append(record.getSourceClassName());
			} else {
			    sb.append(record.getLoggerName());
			}
			if (record.getSourceMethodName() != null) {	
			    sb.append(" ");
			    sb.append(record.getSourceMethodName());
			}
			sb.append(lineSeparator);
		}
	}

	private void appendTimestamp(LogRecord record, StringBuilder sb) {
		if(WriteTimeStamp)
		{
			// Minimize memory allocations here.
			date.setTime(record.getMillis());
			args[0] = date;
			StringBuffer text = new StringBuffer();
			if (formatter == null) {
			    formatter = new MessageFormat(format);
			}
			formatter.format(args, text, null);
			sb.append(text);
			sb.append(" ");
		}
	}
}
