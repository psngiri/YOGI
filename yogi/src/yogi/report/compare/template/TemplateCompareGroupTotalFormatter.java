package yogi.report.compare.template;

import java.util.List;

import yogi.base.io.Formatter;
import yogi.report.compare.CompareGroup;

public class TemplateCompareGroupTotalFormatter<T> implements Formatter<CompareGroup<T>> {
	private CompareReportTemplate<T> reportTemplate;
	private CompareGroupValuesGenerator<T> compareGroupValuesGenerator;
	
	public TemplateCompareGroupTotalFormatter(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
		this.compareGroupValuesGenerator = new CompareGroupValuesGenerator<T>(reportTemplate, true);
	}

	public String format(CompareGroup<T> compareGroup) {
		List<String> values = compareGroupValuesGenerator.generateValues(compareGroup);
		String columnSeparator = reportTemplate.getColumnSeparator();
		StringBuilder rowStringBuilder = new StringBuilder();
		rowStringBuilder.append(values.get(0));
		for(int i = 1; i < values.size(); i ++)
		{
			rowStringBuilder.append(columnSeparator);
			rowStringBuilder.append(values.get(i));
		}
		return rowStringBuilder.toString();
	}

}
