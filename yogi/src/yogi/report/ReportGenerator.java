package yogi.report;

import java.util.Comparator;
import java.util.List;

import yogi.base.Selector;
import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.node.Node;
import yogi.report.group.Group;
import yogi.report.group.GroupBy;
import yogi.report.group.GroupGenerator;
import yogi.report.group.GroupProcessor;

public class ReportGenerator<T>{
	private GroupGenerator<T> groupGenerator;
	private ReportBuilder<T, Group<T>> reportBuilder;
	private ImmutableList<T> items;

	public ReportGenerator(LineWriter lineWriter) {
		this(lineWriter,new GroupGenerator<T>(), null);
	}
	
	public ReportGenerator(LineWriter lineWriter, GroupGenerator<T> groupGenerator, ImmutableList<T> items) {
		super();
		this.groupGenerator = groupGenerator;
		reportBuilder = new ReportBuilder<T, Group<T>>();
		reportBuilder.setLineWriter(lineWriter);
		this.items = items;
	}
	
	public GroupGenerator<T> getGroupGenerator() {
		return groupGenerator;
	}
	public ReportBuilder<T, Group<T>> getReportBuilder() {
		return reportBuilder;
	}
	public Comparator<T> getComparator() {
		return groupGenerator.getComparator();
	}

	public void setComparator(Comparator<T> comparator) {
		groupGenerator.setComparator(comparator);
	}

	public Footer getReportFooter() {
		return reportBuilder.getReportFooter();
	}

	public void setReportFooter(Footer footer) {
		reportBuilder.setReportFooter(footer);
	}

	public void setGroupComparator(Comparator<Group<T>> groupComparator)
	{
		groupGenerator.setGroupComparator(groupComparator);
	}
	
	public void setGroupProcessor(GroupProcessor<T> groupProcessor)
	{
		groupGenerator.setGroupProcessor(groupProcessor);
	}
	
	public void setGroupBys(List<? extends GroupBy<T>> groupBys) {
		groupGenerator.setGroupBys(groupBys);
	}

	public Header getReportHeader() {
		return reportBuilder.getReportHeader();
	}

	public void setReportHeader(Header header) {
		reportBuilder.setReportHeader(header);
	}

	public ImmutableList<T> getItems() {
		return items;
	}

	protected void setItems(ImmutableList<T> items) {
		this.items = items;
	}

	public Selector<? super T> getSelector() {
		return groupGenerator.getSelector();
	}

	public void setSelector(Selector<? super T> selector) {
		groupGenerator.setSelector(selector);
	}

	public void setGroupSelector(Selector<Group<T>> groupSelector) {
		groupGenerator.setGroupSelector(groupSelector);
	}
	
	public boolean isIgnoreConsecutiveSameLines() {
		return reportBuilder.isIgnoreConsecutiveSameLines();
	}
	public void setIgnoreConsecutiveSameLines(boolean ignoreConsecutiveSameLines) {
		reportBuilder.setIgnoreConsecutiveSameLines(ignoreConsecutiveSameLines);
	}

	public void generateReport()
	{
		reportBuilder.build(generateGroups());
	}
	
	protected Node<Group<T>> generateGroups() {
		return groupGenerator.generateGroups(items);
	}

}
