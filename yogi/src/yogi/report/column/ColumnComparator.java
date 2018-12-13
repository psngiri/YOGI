package yogi.report.column;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ColumnComparator<T> implements Comparator<T> {
	private List<ColumnDefinition<T>> columns;
	private Set<ColumnDefinition<T>> descendingColumns= new HashSet<ColumnDefinition<T>>();
	public ColumnComparator() {
		super();
		columns = new ArrayList<ColumnDefinition<T>>();
	}
	
	public ColumnComparator(ColumnDefinition<T>... columnDefinitions ) {
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
	
	public ColumnComparator(List<ColumnDefinition<T>> columns) {
		super();
		this.columns = columns;
	}

	public void setColumns(List<ColumnDefinition<T>> columns) {
		this.columns = columns;
	}

	public int compare(T o1, T o2) {
		int compare = 0;
		for(ColumnDefinition<T> columnDefinition: columns)
		{
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			if(columnWorker != null)
			{
				compare = columnWorker.compare(o1, o2);
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
