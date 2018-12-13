package yogi.report.column;

import java.util.ArrayList;
import java.util.List;

import yogi.base.Selector;
import yogi.report.condition.Condition;

public class ColumnSelector<T, C> implements Selector<T>{
	private ColumnDefinitionBaseImpl<T,C> column;
	private List<Condition<C>> conditions;
	
	public ColumnSelector(ColumnDefinitionBaseImpl<T, C> column) {
		super();
		this.column = column;
		this.conditions = new ArrayList<Condition<C>>();
	}

	public void addCondition(Condition<C> condition)
	{
		conditions.add(condition);
		condition.setFormatter(column.getFormatter());
	}
	
	public boolean select(T item) {
        C value = column.getColumnWorker().getValue(item);
        for(Condition<C> condition: conditions)
        {
            if(condition.satisfied(value)) return true;
        }
        return false;
	}

	public ColumnDefinitionBaseImpl<T, C> getColumn() {
		return column;
	}

	public  List<Condition<C>> getConditions() {
		return conditions;
	}
	

}
