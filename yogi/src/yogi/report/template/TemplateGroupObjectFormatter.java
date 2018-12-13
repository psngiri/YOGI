package yogi.report.template;

import java.util.List;

import yogi.report.GroupObjectFormatter;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnWorker;
import yogi.report.group.Group;

public class TemplateGroupObjectFormatter<T> extends GroupObjectFormatter<Group<T>> {
	private ReportTemplate<T> reportTemplate;
	StringBuilder rowStringBuilder = new StringBuilder();
	
	public TemplateGroupObjectFormatter(ReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}
	
	public String format(Group<T> group, int indexInGroup) {
		List<ColumnDefinition<T>> columns = reportTemplate.getColumns();
		String columnSeparator = reportTemplate.getColumnSeparator();
		rowStringBuilder.delete(0, rowStringBuilder.length());
		appendColumn(columns.get(0), rowStringBuilder, group, indexInGroup);
		for(int columnIndex = 1; columnIndex < columns.size(); columnIndex ++)
		{
			rowStringBuilder.append(columnSeparator);
			appendColumn(columns.get(columnIndex), rowStringBuilder, group, indexInGroup);
		}
		return rowStringBuilder.toString();
	}

	
	private void appendColumn(ColumnDefinition<T> columnDefinition, StringBuilder headerStringBuilder, Group<T> group, int indexInGroup) {
		ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
		String value = null;
		if(columnWorker != null) value = columnDefinition.format(columnWorker.getValue(group, indexInGroup));
		headerStringBuilder.append(TemplateUtil.format(value, columnDefinition.getWidth()));
	}
}
