package yogi.report.column;

import java.util.List;

import yogi.report.group.Group;
import yogi.report.group.GroupProcessor;

public class ColumnGroupProcessor<T> implements GroupProcessor<T>{
	
	List<ColumnDefinition<T>> columns;
	
	public ColumnGroupProcessor(List<ColumnDefinition<T>> columns) {
		super();
		this.columns = columns;
	}
	
	public void reset()
	{
		for(ColumnDefinition<T> columnDefinition: getColumns())
		{
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			if(columnWorker != null) columnWorker.getColumnGroupWorker().reset();
		}
	}

	public List<ColumnDefinition<T>> getColumns()
	{
		return columns;
	}

	public void processObject(Group<T> group, int indexInGroup, int multiplier)
	{
		for(ColumnDefinition<T> columnDefinition: getColumns())
		{
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			if(columnWorker != null) columnWorker.getColumnGroupWorker().processObject(group, indexInGroup, multiplier);
		}
	}
	
	public Object[] getValues(Group<T> group)
	{
		List<ColumnDefinition<T>> columns = getColumns();
		Object[] rtnValue = new Object[getColumns().size()];
		for(int i = 0; i < columns.size(); i ++)
		{
			ColumnDefinition<T> columnDefinition = columns.get(i);
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			Object objectValue = group.getKeyValue(columnDefinition.getName());
			if(objectValue == null)
			{
			 if(columnWorker != null) objectValue = columnWorker.getGroupValue(group);
			}
			rtnValue[i] = objectValue;
		}
		return rtnValue;
	}
}
