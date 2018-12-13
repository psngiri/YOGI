package yogi.report.function.interval;

import java.util.ArrayList;
import java.util.List;

import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.report.function.Function;

public class IntervalAddFunction implements Function<List<Interval>>{
	List<Interval> intervals;
	public void reset() {
		intervals = new ArrayList<Interval>();
	}

	public void process(List<Interval> object, int multiplier) {
		if(object == null) return;
		intervals = Intervals.add(intervals, object);
	}

	public List<Interval> getValue() {
		return intervals;
	}

}
