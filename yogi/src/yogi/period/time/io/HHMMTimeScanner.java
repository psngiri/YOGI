package yogi.period.time.io;


import yogi.base.app.ErrorReporter;
import yogi.base.io.Scanner;
import yogi.period.time.Time;
import yogi.period.time.TimeCreator;

public class HHMMTimeScanner implements Scanner<Time, String> {
	TimeCreator creator = new TimeCreator();
	public TimeCreator scan(String record) {
		int timeInMinutes = getTimeInMinutes(record);
		creator.setTime(timeInMinutes);
		return creator;
	}
	
	public static int getTimeInMinutes(String record) {
		if(record.length() > 4 || record.length() < 1) throw new RuntimeException("Time should be in HHMM format between 0 and 2400 :" + record);
		String hoursString;
		String minutesString;
		switch(record.length())
		{
		case 1:
		case 2:
			hoursString = "0";
			minutesString = record;
			break;
		case 3:
			hoursString = record.substring(0,1);
			minutesString = record.substring(1,3);
			break;
		default:
			hoursString = record.substring(0,2);
			minutesString = record.substring(2,4);
		}
		int hours = Integer.parseInt(hoursString);
		int minutes = Integer.parseInt(minutesString);
		if(minutes > 59) ErrorReporter.get().error("Minutes greater than 59", record);
		int timeInMinutes = hours*60 + minutes;
		return timeInMinutes;
	}

}
