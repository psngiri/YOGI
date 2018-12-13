package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.column.ColumnDefinition;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.function.CompareFunction;
import yogi.report.group.Group;
import yogi.report.template.TemplateUtil;

public class CompareGroupValuesGenerator<T> {
	private CompareReportTemplate<T> reportTemplate;
	private boolean pad = false;
	
	public CompareGroupValuesGenerator(CompareReportTemplate<T> reportTemplate, boolean pad) {
		super();
		this.reportTemplate = reportTemplate;
		this.pad = pad;
	}
	public List<String> generateValues(CompareGroup<T> compareGroup) {
		int numberOfDataSets = reportTemplate.getNumberOfDataSets();
		Object[][] groupValues = new Object[numberOfDataSets][];
		for(int i = 0; i < numberOfDataSets; i++)
		{
			Group<T> group = compareGroup.getGroup(i);
			if(group != null) groupValues[i] = group.getValues();
		}
		List<String> values = formatTotal(groupValues);
		return values;
	}
	
	protected List<ColumnDefinition<T>> getColumns() {
		return reportTemplate.getColumns();
	}
	
	private List<String> formatTotal(Object[][] groupValues)
	{
		List<String> values = new ArrayList<String>();
		List<ColumnDefinition<T>> columns = getColumns();
		for(int i = 0; i < columns.size(); i ++)
		{
			appendColumn(groupValues, columns.get(i), values);
		}
		return values;
	}

	private void appendColumn(Object[][] groupValues, ColumnDefinition<T> columnDefinition, List<String> values) {
		if(reportTemplate.getCompareFunctions(columnDefinition.getIndex()).isEmpty()) appendKeyColumn(groupValues, columnDefinition, values);
		else appendValueColumn(groupValues, columnDefinition, values);
	}
	
	private void appendValueColumn(Object[][] groupValues, ColumnDefinition<T> columnDefinition, List<String> values) {
		int columnIndex = columnDefinition.getIndex();
		int numberOfDataSets = reportTemplate.getNumberOfDataSets();
		Object[] compareGroupValues = new Object[numberOfDataSets];
		for(int i = 0; i < numberOfDataSets; i++)
		{
			Object[] myValues = groupValues[i];
			if(myValues != null) compareGroupValues[i] = myValues[columnIndex];
			appendValue(values, columnDefinition, compareGroupValues[i]);
		}
		for(CompareFunction compareFunction: reportTemplate.getCompareFunctions(columnIndex))
		{
			appendValue(values, columnDefinition, compareFunction.compare(compareGroupValues));
		}
	}

	private void appendValue(List<String> values2, ColumnDefinition<T> columnDefinition, Object object) {
		String value = columnDefinition.format(object);
		if(pad) value = TemplateUtil.format(value, columnDefinition.getWidth());
		values2.add(value);
	}

	private void appendKeyColumn(Object[][] groupValues, ColumnDefinition<T> columnDefinition, List<String> values) {
		Object[] myValues = null;
		int i = 0;
		while(myValues == null)
		{
			myValues = groupValues[i];
			i++;
		}
		String value = columnDefinition.format(myValues[columnDefinition.getIndex()]);
		if(pad) value = TemplateUtil.format(value, columnDefinition.getWidth());
		values.add(value);
	}


}
