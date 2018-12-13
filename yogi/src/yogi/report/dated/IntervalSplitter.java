package yogi.report.dated;

import java.util.List;

import yogi.period.interval.Interval;

public interface IntervalSplitter {
	List<Interval> split(Interval interval);
}
