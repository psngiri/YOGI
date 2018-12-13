package yogi.report.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.util.collections.IndexItem;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.node.Node;
import yogi.report.column.GroupColumnComparator;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;
import yogi.report.group.GroupGenerator;

public class CompareGroupGenerator<T> {
	private GroupGenerator<T> groupGenerator;

	public CompareGroupGenerator(GroupGenerator<T> groupGenerator) {
		super();
		this.groupGenerator = groupGenerator;
	}

	public Node<CompareGroup<T>> generateCompareGroups(ImmutableList<T>... itemLists)
	{
		CompareGroup<T> rootCompareGroup = generateRootCompareGroup(itemLists);
		Node<CompareGroup<T>> rootCompareGroupNode = new Node<CompareGroup<T>>(null, rootCompareGroup);
		List<? extends GroupBy<T>> groupBys = groupGenerator.getGroupBys();
		if(groupBys == null|| groupBys.isEmpty()) return rootCompareGroupNode;
		generateCompareGroups(0, rootCompareGroupNode);
		return rootCompareGroupNode;
	}

	@SuppressWarnings("unchecked")
	public CompareGroup<T> generateRootCompareGroup(ImmutableList<T>... itemLists) {
		Group<T>[] rootGroups = new Group[itemLists.length];
		for(int i = 0; i< itemLists.length; i++)
		{
			rootGroups[i] = groupGenerator.generateRootGroup(itemLists[i]);
		}
		CompareGroup<T> rootCompareGroup = new CompareGroup<T>(rootGroups);
		return rootCompareGroup;
	}
	
	private void generateCompareGroups(int groupIndex, Node<CompareGroup<T>> compareGroupNode) {
		CompareGroup<T> compareGroup = compareGroupNode.getData();
		List<CompareGroup<T>> compareGroups = generateCompareGroups(groupIndex, compareGroup);
		for(CompareGroup<T> newCompareGroup: compareGroups)
		{
			generateCompareGroups(groupIndex, compareGroupNode, newCompareGroup);
		}
	}
	
	private void generateCompareGroups(int groupIndex, Node<CompareGroup<T>> compareGroupNode, CompareGroup<T> newCompareGroup) {
		Node<CompareGroup<T>> newNode = new Node<CompareGroup<T>>(compareGroupNode, newCompareGroup);
		generateCompareGroups(groupIndex+1, newNode);
	}

	public List<CompareGroup<T>> generateCompareGroups(int groupIndex, CompareGroup<T> compareGroup) {
		List<? extends GroupBy<T>> groupBys = groupGenerator.getGroupBys();
		List<CompareGroup<T>> rtnValue = new ArrayList<CompareGroup<T>>();
		if(groupIndex == groupBys.size()) return rtnValue;
		GroupBy<T> groupBy = groupBys.get(groupIndex);
		List<IndexItem<Group<T>>> children = new ArrayList<IndexItem<Group<T>>>();
		Group<T>[] groups = compareGroup.getGroups();
		for(int i = 0; i < groups.length; i ++)
		{
			Group<T> group = groups[i];
			if(group == null) continue;
			List<Group<T>> generatedGroups = groupGenerator.generateGroups(groupIndex, group);
			for(Group<T> child: generatedGroups)
			{
				children.add(new IndexItem<Group<T>>(child, i));
			}
		}
		if(children.isEmpty()) return rtnValue;
		GroupColumnComparator<T> groupColumnComparator;
		if(groupBy != null)  groupColumnComparator = new GroupColumnComparator<T>(groupBy.getColumns());
		else groupColumnComparator = new GroupColumnComparator<T>();
		Collections.sort(children, new MyComparator<T>(groupColumnComparator));
		IndexItem<Group<T>> lastGroupIndexItem = null;
		CompareGroup<T> childCompareGroup = new CompareGroup<T>(groups.length);
		for(IndexItem<Group<T>> groupIndexItem: children)
		{
			if(lastGroupIndexItem != null && groupColumnComparator.compare(lastGroupIndexItem.getItem(), groupIndexItem.getItem()) != 0)
			{
				rtnValue.add(childCompareGroup);
				childCompareGroup = new CompareGroup<T>(groups.length);
			}
			childCompareGroup.setGroup(groupIndexItem.getIndex(), groupIndexItem.getItem());
			lastGroupIndexItem = groupIndexItem;
		}
		rtnValue.add(childCompareGroup);
		return rtnValue;
	}
	
	static class MyComparator<T> implements Comparator<IndexItem<Group<T>>>
	{
		private GroupColumnComparator<T> groupColumnComparator;
		
		public MyComparator(GroupColumnComparator<T> groupColumnComparator) {
			super();
			this.groupColumnComparator = groupColumnComparator;
		}

		public int compare(IndexItem<Group<T>> o1, IndexItem<Group<T>> o2) {
			int rtnValue = groupColumnComparator.compare(o1.getItem(), o2.getItem());
			if(rtnValue != 0) return rtnValue;
			return o1.getIndex()-o2.getIndex();
		}
		
	}
}
