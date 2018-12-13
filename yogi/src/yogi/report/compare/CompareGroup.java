package yogi.report.compare;

import yogi.report.group.Group;

public class CompareGroup<T> {
	private Group<T>[] groups;

	@SuppressWarnings("unchecked")
	public CompareGroup(int numberOfDataSetsToCompare) {
		super();
		groups = new Group[numberOfDataSetsToCompare];
	}
	
	public CompareGroup(Group<T> ... groups) {
		super();
		this.groups = groups;
	}
	
	public CompareGroup<T> setGroup(int index, Group<T> group)
	{
		groups[index] = group;
		return this;
	}
	
	public Group<T>[] getGroups() {
		return groups;
	}

	public Group<T> getGroup(int index)
	{
		return groups[index];
	}
	
}
