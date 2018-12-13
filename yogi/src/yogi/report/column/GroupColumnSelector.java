package yogi.report.column;

import java.util.List;

import yogi.base.Selector;
import yogi.report.condition.Condition;
import yogi.report.group.Group;

public class GroupColumnSelector<T, C> implements Selector<Group<T>>{
	private ColumnDefinitionBaseImpl<T,C> column;
	private Condition<C> condition;
	private ColumnDefinitionBaseImpl<T,C> groupColumn;
	
	public GroupColumnSelector(ColumnDefinitionBaseImpl<T, C> column, Condition<C> condition, ColumnDefinitionBaseImpl<T, C> groupColumn) {
		super();
		this.column = column;
		this.condition = condition;
		this.groupColumn = groupColumn;
		condition.setFormatter(column.getFormatter());
		
	}

	public GroupColumnSelector(ColumnDefinitionBaseImpl<T, C> column, Condition<C> condition) {
		this(column, condition, null);
		
	}
	
	public boolean select(Group<T> group) {
		List<ColumnDefinition<T>> columns = group.getGroupBy().getColumns();
		if(groupColumn!= null && (columns.isEmpty()||columns.get(columns.size()-1)!= groupColumn))  return true;
		C value = column.getColumnWorker().getColumnGroupWorker().getGroupValue(group);
		return condition.satisfied(value);
	}

}
