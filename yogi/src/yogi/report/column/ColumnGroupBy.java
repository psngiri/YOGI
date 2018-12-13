package yogi.report.column;

import java.util.ArrayList;
import java.util.List;

import yogi.report.dated.DatedColumnDefinition;
import yogi.report.group.BaseGroupBy;

public class ColumnGroupBy<T> extends BaseGroupBy<T> {
	private List<ColumnDefinition<T>> columns;
	private ColumnGroupBy<T> previousGroupBy;
	private ColumnComparator<T> columnComparator;
	private List<ColumnDefinition<T>> splitColumns;
	
	public ColumnGroupBy() {
		super();
		columns = new ArrayList<ColumnDefinition<T>>();
	}
	
	public ColumnGroupBy(ColumnGroupBy<T> previousGroupBy, ColumnDefinition<T>... columnDefinitions ) {
		this();
		this.previousGroupBy = previousGroupBy;
		if(previousGroupBy != null) this.setMultiplierCalculator(previousGroupBy.getMultiplierCalculator());
		addColumns(columnDefinitions);
	}

	public ColumnGroupBy<T> addColumns(List<ColumnDefinition<T>> columnDefinitions) {
		for(ColumnDefinition<T> column: columnDefinitions)
		{
			addColumn(column);
		}
		return this;
	}
	
	public ColumnGroupBy(ColumnDefinition<T>... columnDefinitions ) {
		this();
		addColumns(columnDefinitions);
	}

	public void addColumns(ColumnDefinition<T>... columnDefinitions) {
		for(ColumnDefinition<T> column: columnDefinitions)
		{
			addColumn(column);
		}
	}

	public void addColumn(ColumnDefinition<T> column)
	{
		if(!hasKey(column.getName()))
		{
			columns.add(column);
			addKey(column.getName());
		}
		if(column.getSplitter()!= null)
		{
			getSplitColumns().add(column);
		}
	}

	public List<ColumnDefinition<T>> getColumns()
	{
		return columns;
	}
	
	public List<ColumnDefinition<T>> getSplitColumns() {
		if(splitColumns == null) splitColumns = new ArrayList<ColumnDefinition<T>>();
		return splitColumns;
	}

	private ColumnComparator<T> getColumnComparator()
	{
		if(columnComparator == null)
		{
			columnComparator = new ColumnComparator<T>(getColumns());
		}
		return columnComparator;
	}
	
	public int compare(T row1, T row2) {
		return getColumnComparator().compare(row1, row2);
	}
	public List<ColumnDefinition<T>> getAllColumns()
	{
		List<ColumnDefinition<T>> rtnValue = new ArrayList<ColumnDefinition<T>>();
		if(previousGroupBy != null) rtnValue.addAll(previousGroupBy.getAllColumns());
		rtnValue.addAll(columns);
		return rtnValue;
	}

	public ColumnGroupBy<T> getPreviousGroupBy() {
		return previousGroupBy;
	}

	@Override
	public boolean hasKey(String keyName) {
		boolean rtnValue = super.hasKey(keyName);
		if(!rtnValue && previousGroupBy != null) 
		{
			rtnValue = previousGroupBy.hasKey(keyName);
		}
		return rtnValue;
	}

}
