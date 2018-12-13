package yogi.period.time.range.io;

import java.util.List;

import yogi.base.io.Formatter;
import yogi.period.time.Time;
import yogi.period.time.range.TimeRange;

public class TimeRangesFormatter implements Formatter<List<TimeRange>>{
	private Formatter<Time> timeFormatter;
	private char rangeSeparator;
	private String listSeparator;
	private char allDayChar;
	
	public TimeRangesFormatter(Formatter<Time> timeFormatter) {
		this(timeFormatter, '-', "^", '*');		
	}
	
	public TimeRangesFormatter(Formatter<Time> timeFormatter, char rangeSeparator, String listSeparator, char allDayChar) {
		super();
		this.timeFormatter = timeFormatter;
		this.rangeSeparator = rangeSeparator;
		this.listSeparator = listSeparator;
		this.allDayChar = allDayChar;
	}
	
	public String format(List<TimeRange> timeRangeList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for(TimeRange timeRange : timeRangeList) {
			if(timeRange == TimeRange.ALL_DAY) {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(allDayChar);
			} else if(timeRange.getStartTime().getTime() == 1 && timeRange.getEndTime().getTime() == 0) {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(allDayChar);
			} else {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(timeFormatter.format(timeRange.getStartTime()));
				stringBuilder.append(rangeSeparator);
				stringBuilder.append(timeFormatter.format(timeRange.getEndTime()));
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
