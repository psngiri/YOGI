package yogi.report.dated.group;

import java.util.ArrayList;
import java.util.List;

import yogi.base.evaluator.Evaluator;
import yogi.period.interval.Interval;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnGroupBy;
import yogi.report.dated.DatedColumnDefinition;

public class DatedGroupBy<T> extends ColumnGroupBy<T> {
	private List<DatedColumnDefinition<T>> datedColumns;
	
	public DatedGroupBy() {
		super();
		setGroupCreator(new DatedGroupCreator<T>(this));
	}
	
	public DatedGroupBy(DatedGroupBy<T> previousGroupBy, ColumnDefinition<T>... columnDefinitions) {
		super(previousGroupBy, columnDefinitions);
		setGroupCreator(new DatedGroupCreator<T>(this));
		if(previousGroupBy != null)
		{
			this.setIntervalEvaluator(previousGroupBy.getIntervalEvaluator());
		}
	}

	public DatedGroupBy(ColumnDefinition<T>... columnDefinitions) {
		this();
		addColumns(columnDefinitions);

	}
	
	@Override
	public void addColumn(ColumnDefinition<T> column) {
		super.addColumn(column);
		if(column instanceof DatedColumnDefinition)
		{
			getDatedColumns().add((DatedColumnDefinition<T>) column);
		}
	}

	public List<DatedColumnDefinition<T>> getDatedColumns() {
		if(datedColumns == null) datedColumns = new ArrayList<DatedColumnDefinition<T>>();
		return datedColumns;
	}
	
	public void setIntervalEvaluator(Evaluator<T, List<Interval>> intervalEvaluator)
	{
		DatedGroupCreator<T> groupCreator = (DatedGroupCreator<T>) this.getGroupCreator();
		groupCreator.setIntervalEvaluator(intervalEvaluator);
	}
	
	public Evaluator<T, List<Interval>> getIntervalEvaluator()
	{
		DatedGroupCreator<T> groupCreator = (DatedGroupCreator<T>) this.getGroupCreator();
		return groupCreator.getIntervalEvaluator();
	}

}
