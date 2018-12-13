package yogi.report.dated.group;

import java.util.ArrayList;
import java.util.List;

import yogi.report.column.ColumnDefinition;

public class TreeDatedColumnGroupBy<T> {
	private List<ColumnDefinition<T>> columns;
	private DatedGroupBy<T> baseGroupBy;

	public TreeDatedColumnGroupBy(ColumnDefinition<T> ... columnDefinitions) {
		super();
		this.columns = new ArrayList<ColumnDefinition<T>>(columnDefinitions.length);
		for(ColumnDefinition<T> column: columnDefinitions)
		{
			this.columns.add(column);
		}
		baseGroupBy = new DatedGroupBy<T>();
	}

	public List<DatedGroupBy<T>> getGroupBys()
	{
		List<DatedGroupBy<T>> rtnValue = new ArrayList<DatedGroupBy<T>>();
		DatedGroupBy<T> previousGroupBy = getGroupBy();
		rtnValue.add(previousGroupBy);
		for(ColumnDefinition<T> column: columns)
		{
			DatedGroupBy<T> datedGroupBy = new DatedGroupBy<T>(previousGroupBy, column);
			rtnValue.add(datedGroupBy);
			previousGroupBy = datedGroupBy;
		}
		return rtnValue;
	}

	public DatedGroupBy<T> getGroupBy() {
		return baseGroupBy;
	}
}
