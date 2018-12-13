package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnWorker;
import yogi.report.compare.CompareItem;
import yogi.report.compare.GroupIndex;
import yogi.report.compare.function.CompareFunction;
import yogi.report.group.GroupBy;
import yogi.report.template.TemplateUtil;

public class CompareItemValuesGenerator<T> {
	private CompareReportTemplate<T> reportTemplate;
	private boolean pad = false;
	
	public CompareItemValuesGenerator(CompareReportTemplate<T> reportTemplate, boolean pad) {
		super();
		this.reportTemplate = reportTemplate;
		this.pad = pad;
	}

	public List<String> generateValues(CompareItem<T> compareItem, int itemIndex) {
		List<String> values = new ArrayList<String>();
		List<ColumnDefinition<T>> columns = reportTemplate.getColumns();
		for(int columnIndex = 0; columnIndex < columns.size(); columnIndex ++)
		{
			appendColumn(columns.get(columnIndex), values, compareItem, itemIndex);
		}
		return values;
	}

	
	private void appendColumn(ColumnDefinition<T> columnDefinition, List<String> values, CompareItem<T> compareItem, int itemIndex) {
		if(reportTemplate.getCompareFunctions(columnDefinition.getIndex()).isEmpty()) appendKeyColumn(columnDefinition, values, compareItem, itemIndex);
		else appendValueColumn(columnDefinition, values, compareItem, itemIndex);
	}
	private void appendValueColumn(ColumnDefinition<T> columnDefinition, List<String> values, CompareItem<T> compareItem, int itemIndex) {
		ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
		GroupBy<T> groupBy = compareItem.getGroupBy();
		boolean isKey = groupBy != null && groupBy.hasKey(columnDefinition.getName());
		int numberOfDataSets = reportTemplate.getNumberOfDataSets();
		Object[] compareGroupValues = new Object[numberOfDataSets];
		GroupIndex<T> groupIndex = compareItem.getGroupIndex(0, itemIndex);
		if(!isKey && columnWorker != null && groupIndex != null)
		{
			compareGroupValues[0] = columnWorker.getValue(groupIndex.getGroup(), groupIndex.getIndexInGroup());
		}
		appendValue(values, columnDefinition, compareGroupValues[0]);
		for(int dataSetIndex = 1; dataSetIndex < numberOfDataSets; dataSetIndex++)
		{
			groupIndex = compareItem.getGroupIndex(dataSetIndex, itemIndex);
			if(!isKey && columnWorker != null && groupIndex != null)
			{
				compareGroupValues[dataSetIndex] = columnWorker.getValue(groupIndex.getGroup(), groupIndex.getIndexInGroup());
			}
			appendValue(values, columnDefinition, compareGroupValues[dataSetIndex]);
		}
		for(CompareFunction compareFunction: reportTemplate.getCompareFunctions(columnDefinition.getIndex()))
		{
			Object compare = null;
			if(!isKey)
			{
				compare = compareFunction.compare(compareGroupValues);
			}
			appendValue(values, columnDefinition, compare);
		}
	}
	
	private void appendValue(List<String> values, ColumnDefinition<T> columnDefinition, Object object) {
		String value = null;
		value = columnDefinition.format(object);
		if(pad) value = TemplateUtil.format(value, columnDefinition.getWidth());
		values.add(value);
	}

	private void appendKeyColumn(ColumnDefinition<T> columnDefinition, List<String> values, CompareItem<T> compareItem, int itemIndex) {
		ColumnWorker<T> columnWorker = columnDefinition.getColumnWorker();
		String value = null;
		GroupIndex<T> groupIndex = null;
		int dataSetIndex = 0;
		while(groupIndex == null)
		{
			groupIndex = compareItem.getGroupIndex(dataSetIndex, itemIndex);
			dataSetIndex++;
		}
		if(columnWorker != null) value = columnDefinition.format(columnWorker.getValue(groupIndex.getGroup(), groupIndex.getIndexInGroup()));
		if(pad) value = TemplateUtil.format(value, columnDefinition.getWidth());
		values.add(value);
	}


}
