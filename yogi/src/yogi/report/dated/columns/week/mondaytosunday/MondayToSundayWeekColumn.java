package yogi.report.dated.columns.week.mondaytosunday;

import yogi.base.io.Formatter;
import yogi.period.date.range.DateRange;
import yogi.report.dated.columns.week.WeekColumn;
import yogi.report.dated.columns.week.WeekFormatter;


public class MondayToSundayWeekColumn<T> extends WeekColumn<T> {

	public MondayToSundayWeekColumn()
	{
		this("Week", 13, new WeekFormatter(), null);
	}
	
	public MondayToSundayWeekColumn(String name, int width, Formatter<DateRange> formatter, String[] heading) {
		super(name, width, formatter, heading, new MondayToSundayWeekSplitter());
	}
}
