package yogi.report.dated.columns.week.sundaytosaturday;

import yogi.base.io.Formatter;
import yogi.period.date.range.DateRange;
import yogi.report.dated.columns.week.WeekColumn;
import yogi.report.dated.columns.week.WeekFormatter;


public class SundayToSaturdayWeekColumn<T> extends WeekColumn<T> {

	public SundayToSaturdayWeekColumn()
	{
		this("Week", 13, new WeekFormatter(), null);
	}
	
	public SundayToSaturdayWeekColumn(String name, int width, Formatter<DateRange> formatter, String[] heading) {
		super(name, width, formatter, heading, new SundayToSaturdayWeekSplitter());
	}
}
