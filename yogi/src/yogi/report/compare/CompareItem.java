package yogi.report.compare;

import java.util.ArrayList;
import java.util.List;

import yogi.report.group.GroupBy;


public class CompareItem<T> {
	private List<GroupIndex<T>>[] groupIndexes;
	private int numberOfItems = -1;

	@SuppressWarnings("unchecked")
	public CompareItem(int numberOfDataSetsToCompare) {
		super();
		groupIndexes =  new List[numberOfDataSetsToCompare];
	}
	
	public CompareItem<T> setNodeGroup(int dataSetIndex, GroupIndex<T> groupIndex)
	{
		if(groupIndexes[dataSetIndex] == null)
		{
			groupIndexes[dataSetIndex] = new ArrayList<GroupIndex<T>>(1);
		}
		groupIndexes[dataSetIndex].add(groupIndex);
		return this;
	}
	
	public int getNumberOfItems()
	{
		if(numberOfItems == -1)
		{
			for(List<GroupIndex<T>> list: groupIndexes)
			{
				if(list != null && list.size() > numberOfItems) numberOfItems = list.size();
			}
		}
		return numberOfItems;
	}
	
	public GroupIndex<T> getGroupIndex(int dataSetIndex, int itemIndex)
	{
		List<GroupIndex<T>> list = groupIndexes[dataSetIndex];
		if(list != null && itemIndex < list.size()) return list.get(itemIndex);
		return null;
	}
	
	public GroupBy<T> getGroupBy()
	{
		for(List<GroupIndex<T>> groupIndex: groupIndexes)
		{
			if(groupIndex != null) return groupIndex.get(0).getGroup().getGroupBy();
		}
		throw new RuntimeException("CompareItem with all null GroupIndexes");
	}
}
