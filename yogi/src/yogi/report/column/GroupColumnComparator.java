package yogi.report.column;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.report.group.Group;

public class GroupColumnComparator<T> implements Comparator<Group<T>> {
	private List<ColumnDefinition<T>> columns;
	private Set<ColumnDefinition<T>> descendingColumns= new HashSet<ColumnDefinition<T>>();
	
	public GroupColumnComparator() {
		super();
		columns = new ArrayList<ColumnDefinition<T>>();
	}
	
	public GroupColumnComparator(ColumnDefinition<T>... columnDefinitions ) {
		this();
		for(ColumnDefinition<T> column: columnDefinitions)
		{
			addColumn(column);
		}
	}
	
	public void addDescendingColumn(ColumnDefinition<T> column)
	{
		descendingColumns.add(column);
	}
	
	public GroupColumnComparator(List<ColumnDefinition<T>> columns) {
		super();
		this.columns = columns;
	}

	public int compare(Group<T> o1, Group<T> o2) {
		int compare = 0;
		if(columns == null) return compare;
		for(ColumnDefinition<T> columnDefinition: columns)
		{
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			if(columnWorker != null)
			{
				compare = columnWorker.compareGroup(o1, o2);
				if(descendingColumns.contains(columnDefinition))
				{
					compare = - compare;
				}
			}
			if(compare != 0) return compare;
		}
		return compare;
	}

	public void addColumn(ColumnDefinition<T> column)
	{
		if(!columns.contains(column))
		{
			columns.add(column);
		}
	}
	
}
