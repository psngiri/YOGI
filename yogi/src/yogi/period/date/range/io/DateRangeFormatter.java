package yogi.period.date.range.io;

import java.io.Serializable;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.range.DateRange;

public class DateRangeFormatter implements Formatter<DateRange>, Serializable{
	
	private static final long serialVersionUID = -4970011730640204970L;
	private Formatter<Date> dateFormatter;
	private char separator;
	private boolean ignoreSeperator;
	
	
	public DateRangeFormatter(Formatter<Date> dateFormatter, char separator) {
		super();
		this.dateFormatter = dateFormatter;
		this.separator = separator;
		ignoreSeperator = false;
	}

	public DateRangeFormatter(Formatter<Date> dateFormatter) {
		super();
		this.dateFormatter = dateFormatter;
		ignoreSeperator = true;
	}
	public String format(DateRange dateRange) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(dateFormatter.format(dateRange.getStart()));
		if(!ignoreSeperator) stringBuilder.append(separator);
		stringBuilder.append(dateFormatter.format(dateRange.getEnd()));
		return stringBuilder.toString();
	}
}
