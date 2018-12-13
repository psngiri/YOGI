package yogi.report.dated.group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.util.immutable.ImmutableList;
import yogi.period.interval.Interval;
import yogi.report.group.GroupBy;

public class DatedGroupImpl<T> extends BaseDatedGroup<T>{
	private Map<String, Comparable> keyValues;
	private List<Interval>[] itemIntervals;
	
	@SuppressWarnings("unchecked")
	public DatedGroupImpl(ImmutableList<T> items, int startIndex, int endIndex, GroupBy<T> groupBy) {
		super(items, startIndex, endIndex, groupBy);
		itemIntervals = new List[(endIndex - startIndex) +1];
	}

	public DatedGroupImpl(ImmutableList<T> items, int startIndex, int endIndex, GroupBy<T> groupBy, Map<String, Comparable> keyValues) {
		this(items, startIndex, endIndex, groupBy);
		putKeyValues(keyValues);
	}
	
	private void putKeyValues(Map<String, Comparable> keyValues)
	{
		this.keyValues = new HashMap<String, Comparable>(keyValues.size());
		putAllKeyValues(keyValues);
	}

	void putAllKeyValues(Map<String, Comparable> keyValues) {
		this.keyValues.putAll(keyValues);
	}
	
	public Comparable getKeyValue(String keyName)
	{
		if(keyValues == null) return null;
		Comparable keyValue = keyValues.get(keyName);
		return keyValue;
	}

	
	Map<String, Comparable> getKeyValues() {
		return keyValues;
	}

	public void addInterval(int indexInGroup, Interval interval)
	{
		List<Interval> intervals = getIntervals(indexInGroup);
		if(intervals == null)
		{
			intervals = new ArrayList<Interval>();
			setIntervals(indexInGroup, intervals);
		}
		intervals.add(interval);
	}

	public void setIntervals(int indexInGroup, List<Interval> intervals) {
		itemIntervals[indexInGroup] = intervals;
	}

	public List<Interval> getIntervals(int indexInGroup) {
		List<Interval> intervals = itemIntervals[indexInGroup];
		return intervals;
	}
	
}
