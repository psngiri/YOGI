package yogi.period.time.range;

import java.util.Comparator;
import java.util.List;

public class TimeRangesComparator implements Comparator<List<TimeRange>> {
	public int compare(List<TimeRange> timeRangeList1, List<TimeRange> timeRangeList2) {
		return TimeRanges.compare(timeRangeList1, timeRangeList2);
	}

}
