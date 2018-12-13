package yogi.report.group;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;

public class SimpleGroupCreator<T> implements GroupCreator<T>{
	private GroupBy<T> groupBy;

	public SimpleGroupCreator(GroupBy<T> groupBy) {
		super();
		this.groupBy = groupBy;
	}

	protected GroupBy<T> getGroupBy() {
		return groupBy;
	}
	
	public List<Group<T>> create(Group<T> group) {
		List<Group<T>> groups = new ArrayList<Group<T>>();
		ImmutableList<T> items = group.getItems();
		int newStartIndex = group.getStartIndex();
		int index;
		for(index = group.getStartIndex(); index < group.getEndIndex(); index ++)
		{
			if(groupBy.compare(items.get(index), items.get(index+1)) == 0) continue;
			Group<T> newGroup = new GroupImpl<T>(items, newStartIndex, index, groupBy);
			groups.add(newGroup);
			newStartIndex = index+1;
		}
		Group<T> newGroup = new GroupImpl<T>(items, newStartIndex, index, groupBy);
		groups.add(newGroup);
		return groups;
	}

}
