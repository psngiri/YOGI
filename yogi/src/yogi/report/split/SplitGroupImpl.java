package yogi.report.split;

import java.util.Map;

import yogi.base.util.immutable.ImmutableList;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;
import yogi.report.group.GroupMultiplierCalculator;
import yogi.report.group.GroupProcessor;

public class SplitGroupImpl<T> implements Group<T>{
	private ImmutableList<T> items;
	private int startIndex;
	private int endIndex;
	private GroupBy<T> groupBy;
	private Object[] values;
	private Comparable[] keys;
	private Map<String, Integer> keyIndexes;
	private Group<T> parent;
	
	public SplitGroupImpl(ImmutableList<T> items, GroupBy<T> groupBy, Comparable[] keys, Map<String, Integer> keyIndexes, Group<T> parent) {
		super();
		this.items = items;
		this.startIndex = 0;
		this.endIndex = items.size()-1;
		this.groupBy = groupBy;
		this.keys = keys;
		this.keyIndexes = keyIndexes;
		this.parent = parent;
	}


	public int getEndIndex() {
		return endIndex;
	}

	public GroupBy<T> getGroupBy() {
		return groupBy;
	}

	public ImmutableList<T> getItems() {
		return items;
	}

	public T getItem(int indexInGroup)
	{
		return items.get(indexInGroup);
	}

	
	public int getIndexInGroup(int index)
	{
		if(index > endIndex) throw new RuntimeException("Index value not within the group");
		return index;
	}
	
	public int getStartIndex() {
		return startIndex;
	}
	
	public void process(GroupProcessor<T> groupProcessor) {
		groupProcessor.reset();
		for(int i = getStartIndex(); i <= getEndIndex(); i ++)
		{
			int indexInGroup = i - getStartIndex();
			if(!isValid(indexInGroup)) continue;
			groupProcessor.processObject(this, indexInGroup, getMultiplier(indexInGroup));
		}
		values = groupProcessor.getValues(this);
	}

	public int getMultiplier(int indexInGroup) {
		if(groupBy == null) return 1;
		GroupMultiplierCalculator<T> multiplierCalculator = groupBy.getMultiplierCalculator();
		if(multiplierCalculator == null) return 1;
		return multiplierCalculator.getMultiplier(this, indexInGroup);
	}
	
	public Comparable getKeyValue(String keyName)
	{
		Comparable rtnValue = parent.getKeyValue(keyName);
		if(rtnValue != null) return rtnValue;
		Integer index = keyIndexes.get(keyName);
		if(index != null){
			return keys[index];
		}
		return rtnValue;
	}

	public boolean isValid(int indexInGroup)
	{
		return true;
	}
	
	public Object[] getValues()
	{
		return values;
	}

	public ImmutableList<T> getGroupItems() {
		return getItems();
	}

	@Override
	public int getItemIndex(int indexInGroup) {
		return indexInGroup;
	}

}
