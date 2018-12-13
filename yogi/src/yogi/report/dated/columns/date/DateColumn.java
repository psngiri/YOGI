package yogi.report.dated.columns.date;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;


public class DateColumn<T> extends DatedColumnDefinitionImpl<T, Date> {

	public DateColumn()
	{
		this("Date", 6, new MyFormatter(), null);
	}
	
	public DateColumn(String name, int width, Formatter<Date> formatter, String[] heading) {
		super(name, width, formatter, heading, new DateIntervalSplitter());
	}

	public Date getValue(Interval interval) {
		return interval.getDateRange().getStart();
	}

	static class MyFormatter implements Formatter<Date>
	{
		public String format(Date object) {
			if(object == null) return "";
			return object.toString();
		}
	}
}
