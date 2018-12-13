package yogi.period.interval.io;

import java.io.Serializable;
import java.util.List;

import yogi.base.io.Formatter;
import yogi.period.date.io.DDMMMYYYYDateFormatter;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.DateRangeFormatter;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;
import yogi.period.interval.Interval;

public class IntervalsFormatter implements Formatter<List<Interval>>, Serializable{
	private static final long serialVersionUID = 6545319188025821812L;
	private Formatter<DateRange> dateRangeFormatter;
	private Formatter<Frequency> frequencyFormatter;
	private char separator;
	private boolean ignoreSeperator;

	public IntervalsFormatter()
	{
		this(new DateRangeFormatter(new DDMMMYYYYDateFormatter(), ' '), new MondayToSundayFrequencyFormatter());
	}
	
	public IntervalsFormatter(Formatter<DateRange> dateRangeFormatter, Formatter<Frequency> frequencyFormatter, char separator) {
		super();
		this.dateRangeFormatter = dateRangeFormatter;
		this.frequencyFormatter = frequencyFormatter;
		this.separator = separator;
		ignoreSeperator = false;
	}
	
	public IntervalsFormatter(Formatter<DateRange> dateRangeFormatter, Formatter<Frequency> frequencyFormatter) {
		super();
		this.dateRangeFormatter = dateRangeFormatter;
		this.frequencyFormatter = frequencyFormatter;
		ignoreSeperator = true;
	}

	public String format(List<Interval> intervals) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first=true;
		for(Interval interval: intervals)
		{	
			if(!first)
				stringBuilder.append("/");
			first=false;
			stringBuilder.append(dateRangeFormatter.format(interval.getDateRange()));
			if(!ignoreSeperator)stringBuilder.append(separator);		
			stringBuilder.append(frequencyFormatter.format(interval.getFrequency()));
		}
		return stringBuilder.toString();
	}

}
