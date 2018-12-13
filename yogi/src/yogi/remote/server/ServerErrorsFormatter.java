package yogi.remote.server;

import java.util.ArrayList;

import yogi.remote.log.BaseLogRecordErrorsFormatter;
import yogi.remote.log.LogRecord;

public class ServerErrorsFormatter extends BaseLogRecordErrorsFormatter
{
	private ArrayList<LogRecord> logRecords = new ArrayList<LogRecord>();
	
	public ServerErrorsFormatter() {
		super();
	}


	public void log(LogRecord logRecord) {
		logRecords.add(logRecord);
	}
	
	public ArrayList<LogRecord> emptyLogRecords()
	{
		ArrayList<LogRecord> rtnValue = logRecords;
		logRecords = new ArrayList<LogRecord>();
		return rtnValue;
	}
}
