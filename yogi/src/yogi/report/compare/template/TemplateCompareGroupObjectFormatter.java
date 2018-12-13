package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.base.io.MultiLineFormatter;
import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareItem;
import yogi.report.compare.CompareItemGenerator;
import yogi.report.compare.KeyColumnComparator;

public class TemplateCompareGroupObjectFormatter<T> implements MultiLineFormatter<CompareGroup<T>> {
	private CompareReportTemplate<T> reportTemplate;
	private CompareItemValuesGenerator<T> compareItemValuesGenerator;
	
	public TemplateCompareGroupObjectFormatter(CompareReportTemplate<T> reportTemplate) {
		super();
		this.reportTemplate = reportTemplate;
		compareItemValuesGenerator = new CompareItemValuesGenerator<T>(reportTemplate, true);
	}
	
	public List<String> format(CompareItem<T> compareItem) {
		String columnSeparator = reportTemplate.getColumnSeparator();
		List<String> rtnValue = new ArrayList<String>();
		for(int itemIndex = 0; itemIndex < compareItem.getNumberOfItems(); itemIndex ++)
		{
			rtnValue.add(formatItem(compareItem, columnSeparator, itemIndex).toString());
		}
		return rtnValue;
	}

	private StringBuilder formatItem(CompareItem<T> compareItem, String columnSeparator, int itemIndex) {
		StringBuilder rowStringBuilder = new StringBuilder();
		List<String> values = compareItemValuesGenerator.generateValues(compareItem, itemIndex);
		rowStringBuilder.append(values.get(0));
		for(int i = 1; i < values.size(); i ++)
		{
			rowStringBuilder.append(columnSeparator);
			rowStringBuilder.append(values.get(i));
		}
		return rowStringBuilder;
	}

	public List<String> format(CompareGroup<T> compareGroup) {
		List<String> rtnValue = new ArrayList<String>();
		CompareItemGenerator<T> compareItemGenerator = new CompareItemGenerator<T>(compareGroup, new KeyColumnComparator<T>((BaseCompareReportTemplate<T>) reportTemplate));
		List<CompareItem<T>> compareItems = compareItemGenerator.generate();
		for(CompareItem<T> compareItem: compareItems)
		{
			rtnValue.addAll(format(compareItem));
		}
		return rtnValue;
	}


}
