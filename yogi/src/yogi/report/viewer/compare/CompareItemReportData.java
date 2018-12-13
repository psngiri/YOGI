package yogi.report.viewer.compare;

import java.util.List;

import yogi.report.compare.CompareGroup;
import yogi.report.compare.CompareItem;
import yogi.report.compare.template.CompareItemValuesGenerator;
import yogi.report.compare.template.CompareReportTemplate;

public class CompareItemReportData<T> implements CompareReportData<T>{
	private CompareItem<T> compareItem;
	private int itemIndex;
	private List<String> data = null;
	private CompareGroup<T> group;
	public CompareItemReportData(CompareGroup<T> group, CompareItem<T> compareItem, int itemIndex) {
		super();
		this.group = group;
		this.compareItem = compareItem;
		this.itemIndex = itemIndex;
	}

	public boolean isGroup()
	{
		return false;
	}

	public Object getValue(CompareReportTemplate<T> reportTemplate, int column)
	{
		if(data == null)
		{
			data = new CompareItemValuesGenerator<T>(reportTemplate, false).generateValues(compareItem, itemIndex);
		}
		return data.get(column);
	}
	
	public CompareGroup<T> getCompareGroup() {
		return group;
	}
}
