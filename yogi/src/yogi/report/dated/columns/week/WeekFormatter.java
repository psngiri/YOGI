package yogi.report.dated.columns.week;

import yogi.base.io.Formatter;
import yogi.period.date.range.DateRange;

public class WeekFormatter implements Formatter<DateRange>
{
	public String format(DateRange object) {
		if(object == null) return "";
		return object.toString();
	}
}