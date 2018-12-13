package yogi.period.time.io;

import yogi.period.time.Time;
import yogi.period.time.TimeCreator;
import yogi.period.time.TimeUtils;

public class HHColonMMTimeScanner implements yogi.base.io.Scanner<Time, String> {
	TimeCreator creator = new TimeCreator();
	public TimeCreator scan(String record) {
		int timeInMinutes = TimeUtils.getTimeInMinutes(record, ':');
		creator.setTime(timeInMinutes);
		return creator;
	}
	
}
