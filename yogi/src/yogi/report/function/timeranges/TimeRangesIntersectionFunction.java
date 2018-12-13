package yogi.report.function.timeranges;

import java.util.ArrayList;
import java.util.List;

import yogi.period.time.range.TimeRange;
import yogi.period.time.range.TimeRanges;
import yogi.report.function.Function;

public class TimeRangesIntersectionFunction implements Function<List<TimeRange>>{
	List<TimeRange> timeRanges = new ArrayList<TimeRange>();
	
	public void reset() {
		timeRanges = new ArrayList<TimeRange>();
	}

	public void process(List<TimeRange> object, int multiplier) {
		if(object == null) return;
		if(timeRanges.isEmpty()) timeRanges = object;
		else timeRanges = TimeRanges.intersection(timeRanges, object);
	}

	public List<TimeRange> getValue() {
		return timeRanges;
	}

}
