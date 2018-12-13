package yogi.report.function.interval;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.report.function.Function;

public class IntervalIntersectionFunction implements Function<List<Interval>>{
	List<Interval> intervals;
	boolean isNew = true;
	
	public void reset() {
		intervals = null;
		isNew = true;
	}

	public void process(List<Interval> object, int multiplier) {
		if(object == null) return;
		if(isNew && intervals == null) {
			intervals = object;
			isNew = false;
		}
		else intervals = Intervals.intersection(intervals, object);
	}

	public List<Interval> getValue() {
		return intervals;
	}

}
