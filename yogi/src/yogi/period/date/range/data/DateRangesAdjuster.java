package yogi.period.date.range.data;

import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.immutable.ImmutableListAdjuster;
import yogi.period.date.range.DateRange;

class DateRangesAdjuster extends ImmutableListAdjuster {
	private static DateRangesAdjuster dateRangesAdjuster = new DateRangesAdjuster();
	
	public static DateRangesAdjuster get()
	{
		return dateRangesAdjuster;
	}
	
	void add(ImmutableList<DateRange> immutablelist, List<DateRange> dateRanges)
	{
		getList(immutablelist).addAll(dateRanges);
	}
	void replace(ImmutableList<DateRange> immutablelist, List<DateRange> dateRanges)
	{
		List<DateRange> list = getList(immutablelist);
		list.clear();
		list.addAll(dateRanges);
	}
}
