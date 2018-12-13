package yogi.report.compare.template;

import java.util.ArrayList;
import java.util.List;

import yogi.report.column.ColumnDefinition;
import yogi.report.compare.function.CompareFunction;
import yogi.report.template.TemplateUtil;

public class CompareHeaderValuesGenerator<T> {
	private boolean buildCompoundHeaders = false;
	private CompareReportTemplate<T> reportTemplate;
	private boolean pad = false;
	
	public CompareHeaderValuesGenerator(CompareReportTemplate<T> reportTemplate, boolean buildCompoundHeaders, boolean pad) {
		super();
		this.reportTemplate = reportTemplate;
		this.buildCompoundHeaders = buildCompoundHeaders;
		this.pad = pad;
	}
	
	public List<List<String>> getHeaderValues() {
		List<ColumnDefinition<T>> columns = reportTemplate.getColumns();
		List<List<String>> headerValues = initializeHeaders();
		appendColumn(columns.get(0), headerValues);
		for(int i = 1; i < columns.size(); i ++)
		{
			appendColumn(columns.get(i), headerValues);
		}
		return headerValues;
	}

	private List<List<String>> initializeHeaders() {
		int headerHeight = getHeaderHeight();
		List<List<String>> headerValues = new ArrayList<List<String>>(headerHeight);
		for(int i = 0; i < headerHeight; i ++)
		{
			headerValues.add(new ArrayList<String>());
		}
		return headerValues;
	}
	
	private int getHeaderHeight()
	{
		int rtnValue = 1;
		for(ColumnDefinition<T> columnDefinition: reportTemplate.getColumns())
		{
			rtnValue = Math.max(columnDefinition.getHeading().length, rtnValue);
		}
		return rtnValue + 1;
	}
	
	private void appendColumn(ColumnDefinition<T> columnDefinition, List<List<String>> headerValues) {
		if(reportTemplate.getCompareFunctions(columnDefinition.getIndex()).isEmpty()) appendKeyColumn(columnDefinition, headerValues);
		else appendValueColumn(columnDefinition, headerValues);
	}
	
	private void appendValueColumn(ColumnDefinition<T> columnDefinition, List<List<String>> headerValues) {
		int lastIndex = headerValues.size()-1;
		if(buildCompoundHeaders)appendCommonHeader(columnDefinition.getHeading(), headerValues, lastIndex, reportTemplate.getCompareColumnsWidth(columnDefinition));
		List<String> compareHeaderValues = headerValues.get(lastIndex);
		for(int i = 0; i < reportTemplate.getNumberOfDataSets(); i++)
		{
			if(!buildCompoundHeaders)appendCommonHeader(columnDefinition.getHeading(), headerValues, lastIndex, columnDefinition.getWidth());
			String dataSetName = reportTemplate.getDataSetName(i);
			if(pad) dataSetName = TemplateUtil.format(dataSetName, columnDefinition.getWidth());
			compareHeaderValues.add(dataSetName);
		}
		for(CompareFunction compareFunction: reportTemplate.getCompareFunctions(columnDefinition.getIndex()))
		{
			if(!buildCompoundHeaders)appendCommonHeader(columnDefinition.getHeading(), headerValues, lastIndex, columnDefinition.getWidth());
			String name = compareFunction.getName();
			if(pad) name = TemplateUtil.format(name, columnDefinition.getWidth());
			compareHeaderValues.add(name);
		}
	}

	private void appendCommonHeader(String[] heading, List<List<String>> headerValues, int lastIndex, int width) {
		for(int i = 0; i < lastIndex; i ++)
		{
			String name = "";
			if(i < heading.length) name = heading[i];
			if(pad) name = TemplateUtil.format(name, width);
			headerValues.get(i).add(name);
		}
	}
	
	private void appendKeyColumn(ColumnDefinition<T> columnDefinition, List<List<String>> headerValues) {
		appendCommonHeader(columnDefinition.getHeading(), headerValues, headerValues.size(), columnDefinition.getWidth());
	}
	

}
