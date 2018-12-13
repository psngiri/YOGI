package yogi.report.column;

import java.util.ArrayList;
import java.util.List;

public class TreeColumnGroupBy<T> {
	private List<ColumnDefinition<T>> columns;
	private ColumnGroupBy<T> baseGroupBy;

	public TreeColumnGroupBy(ColumnDefinition<T> ... columnDefinitions) {
		super();
		this.columns = new ArrayList<ColumnDefinition<T>>(columnDefinitions.length);
		for(ColumnDefinition<T> column: columnDefinitions)
		{
			this.columns.add(column);
		}
		baseGroupBy = new ColumnGroupBy<T>();
	}

	public List<ColumnGroupBy<T>> getGroupBys()
	{
		List<ColumnGroupBy<T>> rtnValue = new ArrayList<ColumnGroupBy<T>>();
		ColumnGroupBy<T> previousGroupBy = getGroupBy();
		rtnValue.add(previousGroupBy);
		for(ColumnDefinition<T> column: columns)
		{
			@SuppressWarnings("unchecked")
			ColumnGroupBy<T> columnGroupBy = new ColumnGroupBy<T>(previousGroupBy, column);
			rtnValue.add(columnGroupBy);
			previousGroupBy = columnGroupBy;
		}
		return rtnValue;
	}

	public ColumnGroupBy<T> getGroupBy() {
		return baseGroupBy;
	}
}
