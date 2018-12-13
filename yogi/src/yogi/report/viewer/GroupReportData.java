package yogi.report.viewer;

import yogi.report.column.ColumnDefinition;
import yogi.report.group.Group;

public class GroupReportData<T> implements ReportData<T>{
	private Group<T> group;
	private String[] data = null;
	public GroupReportData(Group<T> group) {
		super();
		this.group = group;
	}
	public boolean isGroup()
	{
		return true;
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
			Object object = group.getValues()[columnDefinition.getIndex()];
			value = columnDefinition.format(object);
			data[columnDefinition.getIndex()] = value;
		}
		return value;
	}
	
	public Group<T> getGroup() {
		return group;
	}
}
