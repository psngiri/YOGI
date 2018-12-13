package yogi.report.dated.group;

import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.report.group.GroupBy;
import yogi.report.group.GroupImpl;

public abstract class BaseDatedGroup<T> extends GroupImpl<T> implements DatedGroup<T>{

	public BaseDatedGroup(ImmutableList<T> items, int startIndex, int endIndex, GroupBy<T> groupBy) {
		super(items, startIndex, endIndex, groupBy);
	}

	@Override
	public int getMultiplier(int indexInGroup) {
		int multiplier = super.getMultiplier(indexInGroup);
		List<Interval> intervals = getIntervals(indexInGroup);
		if(intervals == null) return 0;
		return multiplier *(int) Intervals.getNumberOfDays(intervals);
	}

	public abstract List<Interval> getIntervals(int indexInGroup);
	
	public boolean isValid(int indexInGroup)
	{
		List<Interval> intervals = getIntervals(indexInGroup);
		return intervals != null && !intervals.isEmpty();
	}
	
	public boolean isValid() {
		for(int i = 0; i <= (getEndIndex() - getStartIndex()); i++)
		{
			if(isValid(i)) return true;
		}
		return false;
	}


}
