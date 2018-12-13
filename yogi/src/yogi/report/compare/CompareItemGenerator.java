package yogi.report.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import yogi.base.util.collections.IndexItem;
import yogi.report.group.Group;

public class CompareItemGenerator<T> {
	CompareGroup<T> compareGroup;
	KeyColumnComparator<T> keyColumnComparator;
	
	public CompareItemGenerator(CompareGroup<T> compareGroup, KeyColumnComparator<T> keyColumnComparator) {
		super();
		this.compareGroup = compareGroup;
		this.keyColumnComparator = keyColumnComparator;
	}

	public List<CompareItem<T>> generate()
	{
		List<IndexItem<GroupIndex<T>>> children = new ArrayList<IndexItem<GroupIndex<T>>>();
		Group<T>[] nodeGroups = compareGroup.getGroups();
		for(int i = 0; i < nodeGroups.length; i ++)
		{
			Group<T> group = nodeGroups[i];
			if(group == null) continue;
			for(int index = group.getStartIndex(); index <= group.getEndIndex(); index++)
			{
				int indexInGroup = group.getIndexInGroup(index);
				if(!group.isValid(indexInGroup)) continue;
				children.add(new IndexItem<GroupIndex<T>>(new GroupIndex<T>(group, indexInGroup), i));
			}
		}
		Collections.sort(children, new MyKeyComparator<T>(keyColumnComparator));
		IndexItem<GroupIndex<T>> lastnodeGroupIndex = null;
		List<CompareItem<T>> compareItems = new ArrayList<CompareItem<T>>();
		CompareItem<T> compareItem = new CompareItem<T>(nodeGroups.length);
		for(IndexItem<GroupIndex<T>> nodeGroupIndex: children)
		{
			if(lastnodeGroupIndex != null && keyColumnComparator.compare(lastnodeGroupIndex.getItem().getItem(), nodeGroupIndex.getItem().getItem())!= 0)
			{
				compareItems.add(compareItem);
				compareItem = new CompareItem<T>(nodeGroups.length);
			}
			compareItem.setNodeGroup(nodeGroupIndex.getIndex(), nodeGroupIndex.getItem());
			lastnodeGroupIndex = nodeGroupIndex;
		}
		compareItems.add(compareItem);		
		return compareItems;
	}
	
	static class MyKeyComparator<T> implements Comparator<IndexItem<GroupIndex<T>>>
	{
		private KeyColumnComparator<T> keyColumnComparator;
		
		public MyKeyComparator(KeyColumnComparator<T> keyColumnComparator) {
			super();
			this.keyColumnComparator = keyColumnComparator;
		}

		public int compare(IndexItem<GroupIndex<T>> o1, IndexItem<GroupIndex<T>> o2) {
			int rtnValue = keyColumnComparator.compare(o1.getItem().getItem(), o2.getItem().getItem());
			if(rtnValue != 0) return rtnValue;
			return o1.getIndex()-o2.getIndex();
		}
		
	}
}
