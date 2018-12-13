package yogi.report.template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import yogi.base.Selector;
import yogi.report.Footer;
import yogi.report.Header;
import yogi.report.ReportGenerator;
import yogi.report.column.ColumnDefinition;
import yogi.report.column.ColumnGroupBy;
import yogi.report.column.ColumnGroupProcessor;
import yogi.report.column.Columns;
import yogi.report.group.Group;
import yogi.report.group.GroupGenerator;

public class BaseReportTemplate<T> implements ReportTemplate<T>{
	private Header reportHeader;
	private Footer reportFooter;
	private Columns<T> columns;
	private String columnSeparator;
	private int width = 0;
	private List<ColumnGroupBy<T>> groupBys = new ArrayList<ColumnGroupBy<T>>();
	private Comparator<T> comparator;
	public static String groupByErrorMessage = "GroupBy columns should be a superset of the last added GroupBy's columns.";
	private Comparator<Group<T>> groupComparator;
	private Selector<Group<T>> groupSelector;
	Selector<? super T> selector;
	
	public BaseReportTemplate() {
		super();
		columns = new Columns<T>();
	}
	
	public void setColumnSeparator(String columnSeparator) {
		this.columnSeparator = columnSeparator;
	}

	protected void setReportFooter(Footer reportFooter) {
		this.reportFooter = reportFooter;
	}

	protected void setReportHeader(Header reportHeader) {
		this.reportHeader = reportHeader;
	}

	public void addColumn(ColumnDefinition<T> column)
	{
		columns.add(column);
	}
	
	public void addColumns(ColumnDefinition<T>... columns)
	{
		for(ColumnDefinition<T> column: columns)
		{
			addColumn(column);
		}
	}
	
	public void setColumn(int index, ColumnDefinition<T> column)
	{
		columns.set(index, column);
	}
	
	
	public Selector<? super T> getSelector() {
		return selector;
	}

	public void setSelector(Selector<? super T> selector) {
		this.selector = selector;
	}

	public Footer getReportFooter() {
		return reportFooter;
	}
	public Header getReportHeader() {
		return reportHeader;
	}

	public List<ColumnDefinition<T>> getColumns() {
		return columns.get();
	}

	public String getColumnSeparator() {
		return columnSeparator;
	}

	public int getColumnSeparatorWidth() {
		return getColumnSeparator().length();
	}

	public int getWidth() {
		if(width == 0) width = computeWidth();
		return width;
	}
	
	protected int computeWidth()
	{
		int rtnValue = 0;
		for(ColumnDefinition<T> columnDefinition: getColumns())
		{
			rtnValue = rtnValue + columnDefinition.getWidth();
		}
		rtnValue = rtnValue + getColumnSeparator().length()* (getColumns().size() -1);
		return rtnValue;
	}

	public List<ColumnGroupBy<T>> getGroupBys() {
		return groupBys;
	}

	public ColumnGroupBy<T> getLastGroupBy()
	{
		int size = groupBys.size();
		if(size == 0) return null;
		return groupBys.get(size-1);
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void addGroupBys(List<? extends ColumnGroupBy<T>> groupBys)
	{
		for(ColumnGroupBy<T> groupBy: groupBys)
		{
			addGroupBy(groupBy);
		}
	}
	
	public void addGroupBy(ColumnGroupBy<T> groupBy)
	{
		int size = groupBys.size();
		ColumnGroupBy<T> previousGroupBy = groupBy.getPreviousGroupBy();
		if(size > 0)
		{
			if(previousGroupBy == null) throw new RuntimeException("Previous GroupBy need to be set.");
			if(getLastGroupBy() != previousGroupBy) throw new RuntimeException("GroupBy's previous GroupBy is wrong");
		}else
		{
			if(previousGroupBy != null) throw new RuntimeException("Previous GroupBy should be null.");
		}
		groupBys.add(groupBy);
	}
	
	public void setComparator(Comparator<T> comparator)
	{
		this.comparator = comparator;
	}
		
	public void apply(ReportGenerator<T> reportGenerator){
		reportGenerator.setReportHeader(getReportHeader());
		reportGenerator.setReportFooter(getReportFooter());
		reportGenerator.setGroupBys(getGroupBys());
		reportGenerator.setComparator(getComparator());
		reportGenerator.setGroupProcessor(new ColumnGroupProcessor<T>(getColumns()));
		reportGenerator.setGroupComparator(groupComparator);
		reportGenerator.setGroupSelector(groupSelector);
		reportGenerator.setSelector(selector);
	}
	
	public void apply(GroupGenerator<T> groupGenerator){
		groupGenerator.setGroupBys(getGroupBys());
		groupGenerator.setComparator(getComparator());
		groupGenerator.setGroupProcessor(new ColumnGroupProcessor<T>(getColumns()));
		groupGenerator.setGroupComparator(groupComparator);
		groupGenerator.setGroupSelector(groupSelector);
		groupGenerator.setSelector(selector);
	}

	public Comparator<Group<T>> getGroupComparator() {
		return groupComparator;
	}

	public Selector<Group<T>> getGroupSelector() {
		return groupSelector;
	}

	public void setGroupComparator(Comparator<Group<T>> groupComparator) {
		this.groupComparator = groupComparator;
	}

	public void setGroupSelector(Selector<Group<T>> groupSelector) {
		this.groupSelector = groupSelector;
	}
}
