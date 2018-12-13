package yogi.report.dated.columns.week;

import yogi.base.io.Formatter;
import yogi.period.date.range.DateRange;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinitionImpl;
import yogi.report.dated.IntervalSplitter;


public class WeekColumn<T> extends DatedColumnDefinitionImpl<T, DateRange> {

	public WeekColumn(String name, int width, Formatter<DateRange> formatter, String[] heading, IntervalSplitter splitter) {
		super(name, width, formatter, heading, splitter);
	}

	public DateRange getValue(Interval interval) {
		return interval.getDateRange();
	}
}
