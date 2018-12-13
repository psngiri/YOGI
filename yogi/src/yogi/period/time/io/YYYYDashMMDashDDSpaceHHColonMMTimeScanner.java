package yogi.period.time.io;

import java.util.Calendar;
import java.util.GregorianCalendar;

import yogi.period.time.Time;
import yogi.period.time.TimeCreator;
import yogi.period.time.TimeUtils;

public class YYYYDashMMDashDDSpaceHHColonMMTimeScanner implements yogi.base.io.Scanner<Time, String> {
	TimeCreator creator = new TimeCreator();
	public TimeCreator scan(String record) {	
		Calendar date = new GregorianCalendar();
		String[] dateTime = record.split(" ");
		String[] hrMinSec = dateTime[1].split(":");
		date.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hrMinSec[0]));
		date.set(Calendar.MINUTE,Integer.parseInt(hrMinSec[1]));
		int timeInMinutes = TimeUtils.getTimeInMinutes(date.getTime());
		creator.setTime(timeInMinutes);
		return creator;
	}
	
}
