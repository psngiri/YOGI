package yogi.report.dated.columns.month;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateUtil;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;


public class MonthColumn<T> extends DatedColumnDefinitionImpl<T, Integer> {

	public MonthColumn()
	{
		this("Month", 6, new MyFormatter(), null);
	}
	
	public MonthColumn(String name, int width, Formatter<Integer> formatter, String[] heading) {
		super(name, width, formatter, heading, new MonthIntervalSplitter());
	}

	public Integer getValue(Interval interval) {
		Calendar calendar = DateAssistant.get().getCalendar(interval.getDateRange().getStart());
		return calendar.get(Calendar.MONTH);
	}

	static class MyFormatter implements Formatter<Integer>
	{
		public String format(Integer object) {
			if(object == null) return "";
			return DateUtil.getMonth0Base(object);
		}
	}
}
