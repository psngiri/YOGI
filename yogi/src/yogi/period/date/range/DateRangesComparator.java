package yogi.period.date.range;

import java.util.Comparator;
import java.util.List;

public class DateRangesComparator implements Comparator<List<DateRange>> {
	public int compare(List<DateRange> dateRangeList1, List<DateRange> dateRangeList2) {
		return DateRanges.compare(dateRangeList1, dateRangeList2);
	}

}
