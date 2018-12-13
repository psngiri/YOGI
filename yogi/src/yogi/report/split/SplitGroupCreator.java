package yogi.report.split;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yogi.base.indexing.ManyMultiIndexer;
import yogi.base.indexing.MultiIndexMap.Entry;
import yogi.base.util.immutable.ImmutableList;
import yogi.report.column.ColumnDefinition;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;
import yogi.report.group.SimpleGroupCreator;


public class SplitGroupCreator<T> extends SimpleGroupCreator<T>{
	
	public SplitGroupCreator(GroupBy<T> groupBy) {
		super(groupBy);
	}


	@Override
	public List<Group<T>> create(Group<T> group) {
		for(ColumnDefinition<T> column: this.getGroupBy().getColumns()){
			if(column.getSplitter() != null)
			{
				return createSplitGroups(group);
			}
		}
		return super.create(group);
	}


	private List<Group<T>> createSplitGroups(Group<T> group) {
		List<Group<T>> groups = new ArrayList<Group<T>>();
		List<ColumnDefinition<T>> columns = this.getGroupBy().getColumns();
		ManyMultiIndexer<T> splitGroupIndexer = new ManyMultiIndexer<T>(columns.size());
		Map<String, Integer> keyIndexes = new HashMap<String, Integer>(columns.size());
		Comparable[] keys = new Comparable[columns.size()];
		for(int j = 0; j < columns.size(); j++)
		{
			ColumnDefinition<T> column = columns.get(j);
			keyIndexes.put(column.getName(), j);
		}
		
		int startIndex = group.getStartIndex();
		for(int i = startIndex; i <= group.getEndIndex(); i ++)
		{
			int indexInGroup = i - startIndex;
			List<Comparable[]> splits = new ArrayList<Comparable[]>(columns.size());
			for(int j = 0; j < columns.size(); j++)
			{
				ColumnDefinition<T> splitColumn = columns.get(j);
				Object value = splitColumn.getColumnWorker().getValue(group, indexInGroup);
				Splitter splitter = splitColumn.getSplitter();
				if(splitter != null){
					splits.add(splitter.split(value));
				}else{
					Comparable[] split = new Comparable[]{(Comparable) value};
					splits.add(split);
				}
			}		
			T item = group.getItem(indexInGroup);
			addItem(0, splits, keys, item, splitGroupIndexer);
		}
		for(Entry<List<T>> entry:splitGroupIndexer.entrySet()){
			Comparable[] keys2 = new Comparable[columns.size()];
			for(int i = 0; i < keys.length; i ++){
				keys2[i] = (Comparable) entry.getKeys()[i];
			}
			SplitGroupImpl<T> splitGroup = new SplitGroupImpl<T>(new ImmutableList<T>(entry.getValue()), this.getGroupBy(), keys2, keyIndexes, group);
			groups.add(splitGroup);
		}
		Collections.sort(groups, new GroupComparator<T>(columns));
		return groups;
	}

	private void addItem(int i, List<Comparable[]> splits, Comparable[] keys, T item, ManyMultiIndexer<T> splitGroupIndexer){
		if(i == splits.size()){
			Object[] keys1 = keys;
			splitGroupIndexer.index(item, keys1);
			return;
		}
		Comparable[] split = splits.get(i);
		for(int k = 0 ; k < split.length; k++){
			keys[i] = split[k];
			addItem(i+1, splits, keys, item, splitGroupIndexer);				
		}
	}

	static class GroupComparator<T> implements Comparator<Group<T>>
	{
		List<ColumnDefinition<T>> columns;
		
		public GroupComparator(List<ColumnDefinition<T>> columns) {
			super();
			this.columns = columns;
		}

		@SuppressWarnings("unchecked")
		public int compare(Group<T> o1, Group<T> o2) {
			int rtnValue = 0;
			for(int i = 0; i < columns.size(); i ++)
			{
				String name = columns.get(i).getName();
				rtnValue = o1.getKeyValue(name).compareTo(o2.getKeyValue(name));
				if(rtnValue != 0) return rtnValue;
			}
			return rtnValue;
		}

	}

}

