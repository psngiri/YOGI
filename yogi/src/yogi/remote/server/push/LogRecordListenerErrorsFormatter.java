package yogi.remote.server.push;

import java.rmi.RemoteException;

import yogi.remote.client.push.LogRecordListener;
import yogi.remote.log.BaseLogRecordErrorsFormatter;
import yogi.remote.log.LogRecord;

public class LogRecordListenerErrorsFormatter extends BaseLogRecordErrorsFormatter
{
	public static boolean TurnOnErrorStackTrace = true;
	LogRecordListener logRecordListener;
	
	public LogRecordListenerErrorsFormatter(LogRecordListener logRecordListener) {
		super();
		this.logRecordListener = logRecordListener;
	}


	public void log(LogRecord logRecord) {
		try {
			logRecordListener.report(logRecord);
		} catch (RemoteException e) {
			if(TurnOnErrorStackTrace){
				System.out.println("Turning off Error Stack trace please turn on the property (yogi.remote.server.push.LogRecordListenerErrorsFormatter:TurnOnErrorStackTrace) if you need this trace again");
				TurnOnErrorStackTrace = false;
				e.printStackTrace();
			}
		}
	}
	
}
