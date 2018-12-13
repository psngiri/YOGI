package yogi.period.time.io;

public class TimeDbScanner{
	
	public static Long scan(String record) {
			record = record.trim();
				String [] hourMinuteSecond = record.split(":");
				long hour = Long.parseLong(hourMinuteSecond[0]);
				long minute = Long.parseLong(hourMinuteSecond[1]);
				long second = Long.parseLong(hourMinuteSecond[2]);		
			return ((3600*hour) + (60*minute) + second)*1000;
	}

}
