package yogi.report.group;

import yogi.base.util.immutable.ImmutableList;

public interface Group<T> {
	int getEndIndex();
	GroupBy<T> getGroupBy();
	ImmutableList<T> getItems();
	ImmutableList<T> getGroupItems();
	T getItem(int indexInGroup);
	int getItemIndex(int indexInGroup);
	int getIndexInGroup(int index);
	int getStartIndex();
	void process(GroupProcessor<T> groupProcessor);
	int getMultiplier(int indexInGroup);
	Comparable getKeyValue(String keyName);
	boolean isValid(int indexInGroup);
	Object[] getValues();
}
