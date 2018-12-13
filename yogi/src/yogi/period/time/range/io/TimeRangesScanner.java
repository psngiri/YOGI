package yogi.period.time.range.io;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yogi.base.io.Scanner;
import yogi.period.time.Time;
import yogi.period.time.range.TimeRange;
import yogi.period.time.range.TimeRanges;

public class TimeRangesScanner {
	
	public static String LIST_SEPERATOR_CAP = "^";
	private Scanner<Time, String> timeScanner;
	private char rangeSeparator;
	private String listSeparator;
	private String allDayStr;
	
	public TimeRangesScanner(Scanner<Time, String> timeScanner) {
		this(timeScanner, '-', "\\^", "*");
	}

	public TimeRangesScanner(Scanner<Time, String> timeScanner, char rangeSeparator, String listSeparator, String allDayStr) {
		super();
		this.timeScanner = timeScanner;
		this.rangeSeparator = rangeSeparator;
		this.listSeparator = listSeparator;
		this.allDayStr = allDayStr;
	}

	private List<TimeRange> parse(String record) {
		String[] splits = record.split(listSeparator);
		List<TimeRange> timeRangeList = new ArrayList<TimeRange>();
		List<String> strTimeRangeList = Arrays.asList(splits);
		for(String strTimeRange: strTimeRangeList) {
			strTimeRange = strTimeRange.trim();
			if(strTimeRange.equals(allDayStr)) {
				timeRangeList.add(TimeRange.ALL_DAY);
			} else {
				int indexOf = strTimeRange.indexOf(rangeSeparator);
				if(indexOf < 0) throw new RuntimeException(String.format("Separator %1$s expected for time range record: %2$s", rangeSeparator, record));
				Time startTime = timeScanner.scan(strTimeRange.substring(0, indexOf)).create();
				Time endTime = timeScanner.scan(strTimeRange.substring(indexOf + 1)).create();
				timeRangeList.add(new TimeRange(startTime, endTime));
			}
		}
		return timeRangeList;
	}
	
	public List<TimeRange> scan(String value) {
		List<TimeRange> ranges = null;
		try {
			value = value.trim();
			if(value.isEmpty()) {
				return TimeRanges.EMPTY_IMMUTABLE_LIST;		
			}
			if(allDayStr.equals(value)) {
				return TimeRanges.ALL_DAY_LIST;
			}
			ranges = parse(value);
		} catch(Exception e) {
			throw new RuntimeException("TimeRanges input should be in HHMM-HHMM format seperated by " + (listSeparator.indexOf(LIST_SEPERATOR_CAP) == -1 ? listSeparator : LIST_SEPERATOR_CAP) + " : " + value);
		}
		ranges = TimeRanges.compress(ranges);
		return ranges;
	}
}
