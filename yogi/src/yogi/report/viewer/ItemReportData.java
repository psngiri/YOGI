package yogi.report.viewer;

import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnWorker;
import yogi.report.group.Group;

public class ItemReportData<T> implements ReportData<T>{
	private Group<T> group;
	private int indexInGroup;
	private String[] data = null;
	public ItemReportData(Group<T> group, int indexInGroup) {
		super();
		this.group = group;
		this.indexInGroup = indexInGroup;
	}
	public boolean isGroup()
	{
		return false;
	}

	public Object getValue(ColumnDefinition<T> columnDefinition)
	{
		if(data == null)
		{
			data = new String[group.getValues().length];
		}
		String value = data[columnDefinition.getIndex()];
		if(value == null)
		{
			ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
			if(columnWorker != null) value = columnDefinition.format(columnWorker.getValue(group, indexInGroup));
			else value = "";
			data[columnDefinition.getIndex()] = value;
		}
		return value;
	}
	
	public Group<T> getGroup() {
		return group;
	}
}
