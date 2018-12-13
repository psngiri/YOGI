package yogi.report.dated.columns.dom;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.DateAssistant;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;


public class DayOfMonthColumn<T> extends DatedColumnDefinitionImpl<T, Integer> {

	public DayOfMonthColumn()
	{
		this("DayOfMonth", 10, new MyFormatter(), null);
	}
	
	public DayOfMonthColumn(String name, int width, Formatter<Integer> formatter, String[] heading) {
		super(name, width, formatter, heading, new DayOfMonthSplitter());
	}

	public Integer getValue(Interval interval) {
		Calendar calendar = DateAssistant.get().getCalendar(interval.getDateRange().getStart());
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	static class MyFormatter implements Formatter<Integer>
	{
		public String format(Integer object) {
			if(object == null) return "";
			return object.toString();
		}
	}
}
