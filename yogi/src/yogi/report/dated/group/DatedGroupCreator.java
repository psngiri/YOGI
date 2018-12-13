package yogi.report.dated.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.evaluator.Evaluator;
import yogi.base.indexing.MultiIndexer;
import yogi.period.interval.Interval;
import yogi.report.dated.DatedColumnDefinition;
import yogi.report.dated.IntervalSplitter;
import yogi.report.dated.MultiIntervalSplitter;
import yogi.report.group.Group;
import yogi.report.group.SimpleGroupCreator;


public class DatedGroupCreator<T> extends SimpleGroupCreator<T>{
	private Evaluator<T, List<Interval>> intervalEvaluator;
	private List<DatedColumnDefinition<T>> datedColumns;
	
	public DatedGroupCreator(DatedGroupBy<T> groupBy) {
		super(groupBy);
	}

	@Override
	protected DatedGroupBy<T> getGroupBy() {
		return (DatedGroupBy<T>) super.getGroupBy();
	}

	public Evaluator<T, List<Interval>> getIntervalEvaluator() {
		return intervalEvaluator;
	}

	void setIntervalEvaluator(Evaluator<T, List<Interval>> intervalEvaluator) {
		this.intervalEvaluator = intervalEvaluator;
	}

	@Override
	public List<Group<T>> create(Group<T> group) {
		List<Group<T>> rtnValue = new ArrayList<Group<T>>();
		datedColumns = this.getGroupBy().getDatedColumns();
		if(group instanceof DatedGroup)
		{
			if(datedColumns.isEmpty())
			{
				rtnValue.addAll(createSimpleDatedGroups((DatedGroup<T>) group));
			}else
			{
				List<DatedGroup<T>> datedGroups = createDatedGroups(group);
				applyParentsKeyValues(datedGroups, (DatedGroup<T>)group);
				rtnValue.addAll(datedGroups);
			}
		}else
		{
			List<Group<T>> groups = super.create(group);
			if(datedColumns.isEmpty())
			{
				rtnValue.addAll(createDatedGroups(groups));
			}else
			{
				for(Group<T> myGroup: groups)
				{
					rtnValue.addAll(createDatedGroups(myGroup));
				}
			}
		}
		return rtnValue;
	}


	private void applyParentsKeyValues(List<DatedGroup<T>> datedGroups, DatedGroup<T> parentGroup) {
		if(!(parentGroup instanceof DatedGroupImpl)) return;
		DatedGroupImpl<T> parentGroupImpl = (DatedGroupImpl<T>)parentGroup;
		for(DatedGroup<T> datedGroup: datedGroups)
		{
			if (parentGroupImpl.getKeyValues() != null) {
			    ((DatedGroupImpl<T>)datedGroup).putAllKeyValues(parentGroupImpl.getKeyValues());
		    }
		}
	}

	private List<DatedGroup<T>> createDatedGroups(List<Group<T>> groups) {
		List<DatedGroup<T>> rtnValue = new ArrayList<DatedGroup<T>>();
		for(Group<T> myGroup: groups)
		{
			DatedGroupImpl<T> myDatedGroup = new DatedGroupImpl<T>(myGroup.getItems(), myGroup.getStartIndex(), myGroup.getEndIndex(), myGroup.getGroupBy());
			for(int i = myDatedGroup.getStartIndex(); i <= myDatedGroup.getEndIndex(); i ++)
			{
				int indexInGroup = myDatedGroup.getIndexInGroup(i);
				myDatedGroup.setIntervals(indexInGroup, intervalEvaluator.evaluate(myDatedGroup.getItem(indexInGroup)));
			}
			rtnValue.add(myDatedGroup);
		}
		return rtnValue;
	}

	private List<DatedGroup<T>> createSimpleDatedGroups(DatedGroup<T> group) {
		List<Group<T>> groups = super.create(group);
		List<DatedGroup<T>> rtnValue = new ArrayList<DatedGroup<T>>();
		for(Group<T> myGroup: groups)
		{
			rtnValue.add(new SimpleDatedGroup<T>(group, myGroup.getStartIndex(), myGroup.getEndIndex(), myGroup.getGroupBy()));
		}
		return rtnValue;
	}

	private List<DatedGroup<T>> createDatedGroups(Group<T> group) {
		List<DatedGroup<T>> groups = new ArrayList<DatedGroup<T>>();
		MultiIndexer<DatedGroupImpl<T>> datedGroupIndexer = new MultiIndexer<DatedGroupImpl<T>>(datedColumns.size());
		Map<String, Comparable> keyValues = new HashMap<String, Comparable>(datedColumns.size());
		Comparable[] keys = new Comparable[datedColumns.size()];
		IntervalSplitter intervalSplitter = getIntervalSplitter();
		
		int startIndex = group.getStartIndex();
		for(int i = startIndex; i <= group.getEndIndex(); i ++)
		{
			int indexInGroup = i - startIndex;
			List<Interval> intervals = getIntervals(group, indexInGroup);
			if(intervals == null) continue;
			for(Interval interval: intervals)
			{
				List<Interval> splits = intervalSplitter.split(interval);
				for(Interval splitInterval: splits)
				{
					setKeys(keys, splitInterval);
					DatedGroupImpl<T> datedGroup = getGroup(group, groups, datedGroupIndexer, keyValues, keys);
					datedGroup.addInterval(indexInGroup, splitInterval);
				}
			}
		}
		groups = removeInvalidGroups(groups);
		Collections.sort(groups, new GroupComparator<T>(datedColumns));
		return groups;
	}

	private List<DatedGroup<T>> removeInvalidGroups(List<DatedGroup<T>> groups) {
		List<DatedGroup<T>> rtnValue = new ArrayList<DatedGroup<T>>();
		for(DatedGroup<T> group:groups)
		{
			if(group.isValid()){
				rtnValue.add(group);
			}
		}
		return rtnValue;
	}

	private List<Interval> getIntervals(Group<T> group, int indexInGroup) {
		if(group instanceof DatedGroup)
		{
			DatedGroup<T> datedGroup = (DatedGroup<T>) group;
			return datedGroup.getIntervals(indexInGroup);
		}
		return intervalEvaluator.evaluate(group.getItem(indexInGroup));
	}

	static class GroupComparator<T> implements Comparator<Group<T>>
	{
		List<DatedColumnDefinition<T>> datedColumns;
		
		public GroupComparator(List<DatedColumnDefinition<T>> datedColumns) {
			super();
			this.datedColumns = datedColumns;
		}

		@SuppressWarnings("unchecked")
		public int compare(Group<T> o1, Group<T> o2) {
			int rtnValue = 0;
			for(int i = 0; i < datedColumns.size(); i ++)
			{
				String name = datedColumns.get(i).getName();
				rtnValue = o1.getKeyValue(name).compareTo(o2.getKeyValue(name));
				if(rtnValue != 0) return rtnValue;
			}
			return rtnValue;
		}

	}
	private void setKeys(Comparable[] keys, Interval interval) {
		for(int i = 0; i < keys.length; i ++)
		{
			keys[i] = datedColumns.get(i).getValue(interval);
		}
	}

	private IntervalSplitter getIntervalSplitter() {
		if(datedColumns.size() == 1) return datedColumns.get(0).getIntervalSplitter();
		MultiIntervalSplitter multiIntervalSplitter = new MultiIntervalSplitter();
		for(int i = 0; i < datedColumns.size(); i ++)
		{
			multiIntervalSplitter.addSplitter(datedColumns.get(i).getIntervalSplitter());
		}
		return multiIntervalSplitter;
	}

	private DatedGroupImpl<T> getGroup(Group<T> group, List<DatedGroup<T>> groups, MultiIndexer<DatedGroupImpl<T>> datedGroupIndexer, Map<String, Comparable> keyValues, Comparable[] keys) {
		DatedGroupImpl<T> datedGroup = datedGroupIndexer.get((Object[])keys);
		if(datedGroup == null) 
		{
			for(int i = 0; i < keys.length; i ++)
			{
				DatedColumnDefinition<T> datedColumnDefinition = datedColumns.get(i);
				keyValues.put(datedColumnDefinition.getName(), keys[i]);
			}
			datedGroup = createGroup(group, groups, keyValues);
			datedGroupIndexer.index(datedGroup, (Object[])keys);
		}
		return datedGroup;
	}
	
	private DatedGroupImpl<T> createGroup(Group<T> group, List<DatedGroup<T>> groups, Map<String, Comparable> keyValues) {
		DatedGroupImpl<T> myGroup = new DatedGroupImpl<T>(group.getItems(), group.getStartIndex(), group.getEndIndex(), getGroupBy(), keyValues);
		groups.add(myGroup);
		return myGroup;
	}

}

