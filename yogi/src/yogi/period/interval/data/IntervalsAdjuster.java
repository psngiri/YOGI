package yogi.period.interval.data;

import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.immutable.ImmutableListAdjuster;
import yogi.period.interval.Interval;

class IntervalsAdjuster extends ImmutableListAdjuster {
	private static IntervalsAdjuster intervalsAdjuster = new IntervalsAdjuster();
	
	public static IntervalsAdjuster get()
	{
		return intervalsAdjuster;
	}
	
	void add(ImmutableList<Interval> immutablelist, List<Interval> intervals)
	{
		getList(immutablelist).addAll(intervals);
	}
	void replace(ImmutableList<Interval> immutablelist, List<Interval> intervals)
	{
		List<Interval> list = getList(immutablelist);
		list.clear();
		list.addAll(intervals);
	}
}
