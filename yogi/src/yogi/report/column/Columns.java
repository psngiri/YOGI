package yogi.report.column;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;

public class Columns<T> {
	private List<ColumnDefinition<T>> columns;
	
	public Columns() {
		super();
		columns = new ArrayList<ColumnDefinition<T>>();
	}

	public void add(ColumnDefinition<T> column)
	{
		column.getColumnWorker().getColumnGroupWorker().setIndex(columns.size());
		columns.add(column);
	}
	
	public void set(int index, ColumnDefinition<T> column)
	{
		if(index >= columns.size())
		{
			throw new RuntimeException(String.format("Index %s greater than %s", index, columns.size()-1));
		}
		column.getColumnWorker().getColumnGroupWorker().setIndex(index);
		columns.set(index, column);
	}
	
	public ImmutableList<ColumnDefinition<T>> get() {
		return new ImmutableList<ColumnDefinition<T>>(columns);
	}

	public int size() {
		return columns.size();
	}
}
