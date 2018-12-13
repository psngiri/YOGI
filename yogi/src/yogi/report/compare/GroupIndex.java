package yogi.report.compare;

import yogi.report.group.Group;

public class GroupIndex<T>
{
	Group<T> group;
	int indexInGroup;
	public GroupIndex(Group<T> group, int indexInGroup) {
		super();
		this.group = group;
		this.indexInGroup = indexInGroup;
	}
	
	public T getItem()
	{
		return group.getItem(indexInGroup);
	}
	
	public Group<T> getGroup() {
		return group;
	}

	public int getIndexInGroup() {
		return indexInGroup;
	}
	
}