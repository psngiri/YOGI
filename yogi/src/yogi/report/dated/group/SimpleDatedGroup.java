package yogi.report.dated.group;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.report.group.GroupBy;

public class SimpleDatedGroup<T> extends BaseDatedGroup<T>{
	private DatedGroup<T> parent;
	
	public SimpleDatedGroup(DatedGroup<T> parent, int startIndex, int endIndex, GroupBy<T> groupBy) {
		super(parent.getItems(), startIndex, endIndex, groupBy);
		this.parent = parent;
	}

	public Comparable getKeyValue(String keyName)
	{
		return parent.getKeyValue(keyName);
	}

	public List<Interval> getIntervals(int indexInGroup) {
		return parent.getIntervals(parent.getIndexInGroup(getItemIndex(indexInGroup)));
	}
	
}
