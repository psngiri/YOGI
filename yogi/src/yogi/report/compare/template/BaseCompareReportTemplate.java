package yogi.report.compare.template;

import yogi.base.indexing.ManyIndexer;
import yogi.base.util.immutable.ImmutableList;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnGroupProcessor;
import yogi.report.compare.CompareReportGenerator;
import yogi.report.compare.function.CompareFunction;
import yogi.report.template.BaseReportTemplate;

public class BaseCompareReportTemplate<T> extends BaseReportTemplate<T> implements CompareReportTemplate<T>{
	ManyIndexer<Integer, CompareFunction<?>> compareFunctions = new ManyIndexer<Integer, CompareFunction<?>>();
	int numberOfDataSets;
	String[] dataSetNames;
	
	public void setReportFooter(TemplateCompareReportFooter<T> reportFooter) {
		super.setReportFooter(reportFooter);
	}

	public void setReportHeader(TemplateCompareReportHeader<T> reportHeader) {
		super.setReportHeader(reportHeader);
	}
	public void setCompareFunction(int columnIndex, CompareFunction<?> compareFunction)
	{
		compareFunctions.index(columnIndex, compareFunction);
	}
	
	public ImmutableList<CompareFunction<?>> getCompareFunctions(int columnIndex)
	{
		return compareFunctions.get(columnIndex);
	} 
	
	public ManyIndexer<Integer, CompareFunction<?>> getCompareFunctions() {
		return compareFunctions;
	}

	@Override
	protected int computeWidth()
	{
		int rtnValue = 0;
		for(ColumnDefinition<T> columnDefinition: getColumns())
		{
			rtnValue = rtnValue + getCompareColumnsWidth(columnDefinition);
		}
		rtnValue = rtnValue + getColumnSeparatorWidth()* (getColumns().size() -1);
		return rtnValue;
	}

	public int getCompareColumnsWidth(ColumnDefinition<T> columnDefinition) {
		if(getCompareFunctions(columnDefinition.getIndex()).isEmpty()) return columnDefinition.getWidth();
		int compareColumnCount = getNumberOfDataSets() + getCompareFunctions(columnDefinition.getIndex()).size();
		int compareColumnsWidth = (columnDefinition.getWidth()*compareColumnCount) + getColumnSeparatorWidth()* (compareColumnCount -1);
		return compareColumnsWidth;
	}

	public void apply(CompareReportGenerator<T> reportGenerator){
		reportGenerator.setReportHeader(getReportHeader());
		reportGenerator.setReportFooter(getReportFooter());
		reportGenerator.setGroupBys(getGroupBys());
		reportGenerator.setComparator(getComparator());
		reportGenerator.setGroupProcessor(new ColumnGroupProcessor<T>(getColumns()));
	}

	public int getNumberOfDataSets() {
		return numberOfDataSets;
	}

	public void setNumberOfDataSets(int numberOfDataSets) {
		this.numberOfDataSets = numberOfDataSets;
	}

	public String getDataSetName(int dataSetIndex) {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(dataSetIndex).append(")");
		if(dataSetNames != null && dataSetNames.length > dataSetIndex){
			sb.append(" ").append(dataSetNames[dataSetIndex]);
		}
		return sb.toString();
	}

	public void setDataSetNames(String ... names)
	{
		dataSetNames = names;
	}
}
