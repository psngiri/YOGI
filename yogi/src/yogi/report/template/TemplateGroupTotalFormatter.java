package yogi.report.template;

import java.util.List;

import yogi.base.io.Formatter;
import yogi.report.column.ColumnDefinition;
import yogi.report.group.Group;

public class TemplateGroupTotalFormatter<T> implements Formatter<Group<T>> {
	private ReportTemplate<T> reportTemplate;
	StringBuilder rowStringBuilder = new StringBuilder();
	
	public TemplateGroupTotalFormatter(ReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
	}

	public String format(Group<T> group) {
		Object[] groupValues = group.getValues();
		return formatTotal(groupValues);
	}
	
	protected List<ColumnDefinition<T>> getColumns() {
		return reportTemplate.getColumns();
	}
	
	private String formatTotal(Object[] groupValues)
	{
		List<ColumnDefinition<T>> columns = getColumns();
		String columnSeparator = reportTemplate.getColumnSeparator();
		rowStringBuilder.delete(0, rowStringBuilder.length());
		appendColumn(groupValues, columns.get(0), rowStringBuilder);
		for(int columnIndex = 1; columnIndex < columns.size(); columnIndex ++)
		{
			rowStringBuilder.append(columnSeparator);
			appendColumn(groupValues, columns.get(columnIndex), rowStringBuilder);
		}
		return rowStringBuilder.toString();
	}

	private void appendColumn(Object[] groupValues, ColumnDefinition<T> columnDefinition, StringBuilder headerStringBuilder) {
		String value = columnDefinition.format(groupValues[columnDefinition.getIndex()]);
		headerStringBuilder.append(TemplateUtil.format(value, columnDefinition.getWidth()));
	}

}
