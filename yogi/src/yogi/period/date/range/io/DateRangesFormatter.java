package yogi.period.date.range.io;

import java.util.List;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.range.DateRange;

public class DateRangesFormatter implements Formatter<List<DateRange>>{
	private Formatter<Date> dateFormatter;
	private char rangeSeparator;
	private String listSeparator;
	private char allDateChar;
	
	public DateRangesFormatter(Formatter<Date> dateFormatter) {
		this(dateFormatter, '-', "^", '*');		
	}
	
	public DateRangesFormatter(Formatter<Date> dateFormatter, char rangeSeparator, String listSeparator, char allDateChar) {
		super();
		this.dateFormatter = dateFormatter;
		this.rangeSeparator = rangeSeparator;
		this.listSeparator = listSeparator;
		this.allDateChar = allDateChar;
	}
	
	public String format(List<DateRange> dateRangeList) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");
		for(DateRange dateRange : dateRangeList) {
			if(dateRange == DateRange.ALL_DATE) {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(allDateChar);
			} else if(dateRange.getStart() == DateManager.EARLIEST_DATE && dateRange.getEnd() == DateManager.INFINITY) {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(allDateChar);
			} else {
				if(stringBuilder.length() > 1) stringBuilder.append(listSeparator);
				stringBuilder.append(dateFormatter.format(dateRange.getStart()));
				stringBuilder.append(rangeSeparator);
				stringBuilder.append(dateFormatter.format(dateRange.getEnd()));
			}
		}
		stringBuilder.append("]");
		return stringBuilder.toString();
	}
}
