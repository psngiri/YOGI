package yogi.report.dated.columns.year;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.DateAssistant;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;


public class YearColumn<T> extends DatedColumnDefinitionImpl<T, Integer> {

	public YearColumn()
	{
		this("Year", 6, new MyFormatter(), null);
	}
	
	public YearColumn(String name, int width, Formatter<Integer> formatter, String[] heading) {
		super(name, width, formatter, heading, new YearIntervalSplitter());
	}

	public Integer getValue(Interval interval) {
		Calendar calendar = DateAssistant.get().getCalendar(interval.getDateRange().getStart());
		return calendar.get(Calendar.YEAR);
	}

	static class MyFormatter implements Formatter<Integer>
	{
		public String format(Integer object) {
			if(object == null) return null;
			return object.toString();
		}
	}
}
