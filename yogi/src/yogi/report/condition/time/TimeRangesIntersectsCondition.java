package yogi.report.condition.time;

import java.util.List;

import yogi.period.time.io.HHMMTimeScanner;
import yogi.period.time.range.TimeRange;
import yogi.period.time.range.TimeRanges;
import yogi.period.time.range.io.TimeRangesScanner;
import yogi.report.condition.ConditionBaseImpl;


public class TimeRangesIntersectsCondition extends ConditionBaseImpl<List<TimeRange>> {
	
	private List<TimeRange> ranges;
	private TimeRangesScanner scanner = new TimeRangesScanner(new HHMMTimeScanner());
	
	public TimeRangesIntersectsCondition(String value) {
		super(value);
		ranges = scanner.scan(value);		
	}
		
	public boolean satisfied(List<TimeRange> data) {
		return TimeRanges.intersects(data, ranges);
	}

}