package yogi.period.time.range.io;

import yogi.base.io.Formatter;
import yogi.period.time.Time;
import yogi.period.time.range.TimeRange;

public class TimeRangeFormatter implements Formatter<TimeRange>{
	private Formatter<Time> timeFormatter;
	private char separator;
	private boolean ignoreSeperator;
	
	
	public TimeRangeFormatter(Formatter<Time> timeFormatter, char separator) {
		super();
		this.timeFormatter = timeFormatter;
		this.separator = separator;
		ignoreSeperator = false;
	}

	public TimeRangeFormatter(Formatter<Time> timeFormatter) {
		super();
		this.timeFormatter = timeFormatter;
		ignoreSeperator = true;
	}
	public String format(TimeRange timeRange) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(timeFormatter.format(timeRange.getStartTime()));
		if(!ignoreSeperator) stringBuilder.append(separator);
		stringBuilder.append(timeFormatter.format(timeRange.getEndTime()));
		return stringBuilder.toString();
	}
}
