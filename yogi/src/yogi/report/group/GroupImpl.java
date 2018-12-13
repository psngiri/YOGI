package yogi.report.group;

import yogi.base.util.immutable.ImmutableList;

public class GroupImpl<T> implements Group<T>{
	private ImmutableList<T> items;
	private int startIndex;
	private int endIndex;
	private GroupBy<T> groupBy;
	private Object[] values;
	
	public GroupImpl(ImmutableList<T> items, int startIndex, int endIndex) {
		this(items, startIndex, endIndex, null);
	}

	public GroupImpl(ImmutableList<T> items, int startIndex, int endIndex, GroupBy<T> groupBy) {
		super();
		this.items = items;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.groupBy = groupBy;
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
		int index = getItemIndex(indexInGroup);
		return items.get(index);
	}

	public int getItemIndex(int indexInGroup) {
		int index = getStartIndex() + indexInGroup;
		if(index > endIndex) throw new RuntimeException("IndexInGroup value not within the group");
		return index;
	}
	
	public int getIndexInGroup(int index)
	{
		if(index > endIndex) throw new RuntimeException("Index value not within the group");
		return index - getStartIndex();
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
		return null;
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
		return getItems().subList(startIndex, endIndex +1);
	}

}
