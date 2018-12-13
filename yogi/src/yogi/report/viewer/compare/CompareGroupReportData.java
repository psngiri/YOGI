package yogi.report.viewer.compare;

import java.util.List;

import yogi.report.compare.CompareGroup;
import yogi.report.compare.template.CompareGroupValuesGenerator;
import yogi.report.compare.template.CompareReportTemplate;

public class CompareGroupReportData<T> implements CompareReportData<T>{
	private CompareGroup<T> group;
	private List<String> data = null;
	
	public CompareGroupReportData(CompareGroup<T> group) {
		super();
		this.group = group;
	}
	public boolean isGroup()
	{
		return true;
	}

	public Object getValue(CompareReportTemplate<T> reportTemplate, int column)
	{
		if(data == null)
		{
			data = new CompareGroupValuesGenerator<T>(reportTemplate, false).generateValues(group);
		}
		return data.get(column);
	}
	
	public CompareGroup<T> getCompareGroup() {
		return group;
	}
}
