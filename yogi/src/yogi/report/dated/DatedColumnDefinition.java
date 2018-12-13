package yogi.report.dated;

import yogi.period.interval.Interval;
import yogi.report.column.ColumnDefinition;

public interface DatedColumnDefinition<T> extends ColumnDefinition<T>{
	Comparable getValue(Interval interval);
	IntervalSplitter getIntervalSplitter();
}
