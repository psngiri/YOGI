package yogi.report.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.Filter;
import yogi.base.Selector;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.immutable.Immutables;
import yogi.base.util.node.Node;

public class GroupGenerator<T> {
	private Comparator<T> comparator;
	private Selector<? super T> selector;
	private List<? extends GroupBy<T>> groupBys;
	private Selector<Group<T>> groupSelector;
	private GroupProcessor<T> groupProcessor;
	private Comparator<Group<T>> groupComparator;

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public Selector<? super T> getSelector() {
		return selector;
	}

	public void setSelector(Selector<? super T> selector) {
		this.selector = selector;
	}

	public Comparator<Group<T>> getGroupComparator() {
		return groupComparator;
	}

	public void setGroupComparator(Comparator<Group<T>> groupComparator) {
		this.groupComparator = groupComparator;
	}

	public GroupProcessor<T> getGroupProcessor() {
		return groupProcessor;
	}

	public void setGroupProcessor(GroupProcessor<T> groupProcessor) {
		this.groupProcessor = groupProcessor;
	}

	public Selector<Group<T>> getGroupSelector() {
		return groupSelector;
	}

	public void setGroupSelector(Selector<Group<T>> groupSelector) {
		this.groupSelector = groupSelector;
	}

	public List<? extends GroupBy<T>> getGroupBys() {
		return groupBys;
	}

	public void setGroupBys(List<? extends GroupBy<T>> groupBys) {
		this.groupBys = groupBys;
	}

	public Node<Group<T>> generateGroups(ImmutableList<T> items) {
		Group<T> rootGroup = generateRootGroup(items);
		Node<Group<T>> rootGroupNode = createNode(null, rootGroup);
		if(groupBys == null|| groupBys.isEmpty()) return rootGroupNode;
		generateGroups(0, rootGroupNode);
		return rootGroupNode;
	}

	public Group<T> generateRootGroup(ImmutableList<T> items) {
		ImmutableList<T> selectedItems = getFilteredAndSortedItems(items);
		return getRootGroup(selectedItems);
	}

	public Group<T> getRootGroup(ImmutableList<T> selectedItems) {
		Group<T> rootGroup = new GroupImpl<T>(selectedItems, 0, selectedItems.size()-1);
		rootGroup.process(groupProcessor);
		return rootGroup;
	}

	public ImmutableList<T> getFilteredAndSortedItems(ImmutableList<T> items) {
		ImmutableList<T> selectedItems = new ImmutableList<T>(Filter.filter(items, selector));
		//if(selectedItems.isEmpty()) throw new RuntimeException("No data to report on.");
		sort(selectedItems);
		return selectedItems;
	}

	public List<T> getFilteredAndSortedItems(List<T> items) {
		List<T> selectedItems = items;
		if(selector != null) selectedItems = Filter.filter(items, selector);
		if(comparator != null)
		{
			Collections.sort(selectedItems, comparator);
		}
		return selectedItems;
	}
	
	private void generateGroups(int groupIndex, Node<Group<T>> node) {
		Group<T> group = node.getData();
		List<Group<T>> groups = generateGroups(groupIndex, group);
		for(Group<T> newGroup: groups)
		{
			generateGroups(groupIndex, node, newGroup);
		}
	}

	public List<Group<T>> generateGroups(int groupIndex, Group<T> group) {
		if(groupIndex == groupBys.size()) return new ArrayList<Group<T>>();
		GroupBy<T> groupBy = groupBys.get(groupIndex);
		return generateGroups(group, groupBy);
	}

	private List<Group<T>> generateGroups(Group<T> group, GroupBy<T> groupBy) {
		if(group.getItems().isEmpty()) return new ArrayList<Group<T>>();
		Immutables.sort(group.getGroupItems(), groupBy);
		List<Group<T>> groups = groupBy.getGroupCreator().create(group);
		List<Group<T>> rtnValue = new ArrayList<Group<T>>(groups.size());
		for(Group<T> myGroup: groups)
		{
			myGroup.process(groupProcessor);
			if(groupSelector != null && !groupSelector.select(myGroup)) continue;
			rtnValue.add(myGroup);
		}
		if(groupComparator != null)  Collections.sort(rtnValue, groupComparator);
		return rtnValue;
	}

	private void generateGroups(int groupIndex, Node<Group<T>> node, Group<T> newGroup) {
		Node<Group<T>> newNode = createNode(node, newGroup);
		generateGroups(groupIndex+1, newNode);
	}

	private Node<Group<T>> createNode(Node<Group<T>> parentNode, Group<T> group) {
		Node<Group<T>> node = new Node<Group<T>>(parentNode, group);
		return node;
	}
	

	private void sort(ImmutableList<T> items) {
		if(comparator != null)
		{
			Immutables.sort(items, comparator);
		}
	}

}
