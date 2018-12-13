package yogi.period.time.range.io;

import yogi.base.io.Scanner;
import yogi.period.time.Time;
import yogi.period.time.io.HHMMTimeScanner;
import yogi.period.time.range.TimeRange;

public class TimeRangeScanner {
	private Scanner<Time, String> timeScanner;
	private char separator;
	
	
	public TimeRangeScanner() {
		this(new HHMMTimeScanner(), ' ');
	}

	public TimeRangeScanner(char separator) {
		this(new HHMMTimeScanner(), separator);
	}


	public TimeRangeScanner(Scanner<Time, String> timeScanner, char separator) {
		super();
		this.timeScanner = timeScanner;
		this.separator = separator;
	}


	public TimeRange scan(String record)
	{
		int indexOf = record.indexOf(separator);
		if(indexOf < 0) throw new RuntimeException(String.format("Separator %1$s expected for Time range record: %2$s", separator, record));
		Time startTime = timeScanner.scan(record.substring(0, indexOf)).create();
		Time endTime = timeScanner.scan(record.substring(indexOf + 1)).create();
		return new TimeRange(startTime, endTime);
	}
}
