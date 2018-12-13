package yogi.report.viewer;

import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.node.Node;
import yogi.report.group.Group;
import yogi.report.group.GroupGenerator;
import yogi.report.template.BaseReportTemplate;
import yogi.tools.viewers.TreeTableViewer;



public class ReportViewer<T>
{
	private TreeTableViewer<ReportData<T>> treeTableViewer;

	public ReportViewer(List<T> items, BaseReportTemplate<T> reportTemplate) {
		this(items, reportTemplate, "Report Viewer", true);
	}
	public ReportViewer(List<T> items, BaseReportTemplate<T> reportTemplate, String title) {
		this(items, reportTemplate, title, true);
	}
	public ReportViewer(List<T> items, BaseReportTemplate<T> reportTemplate, String title, boolean exitOnWindowClosing) {
		super();
		ImmutableList<T> immutableItems = new ImmutableList<T>(items);
		ReportTreeTableDataHelper<T> reportTreeTableDataHelper = new ReportTreeTableDataHelper<T>(reportTemplate.getColumns());
		GroupGenerator<T> groupGenerator = new GroupGenerator<T>();
		reportTemplate.apply(groupGenerator);
		Group<T> rootGroup = groupGenerator.generateRootGroup(immutableItems);
		Node<ReportData<T>> rootReportNode = new Node<ReportData<T>>(null, new GroupReportData<T>(rootGroup));
		ReportDataGenerator<T> reportDataGenerator = new ReportDataGenerator<T>(groupGenerator);
		reportDataGenerator.generateReportData(0, rootReportNode);
		ReportTreeNodeImpl<ReportData<T>> rootNode = new ReportTreeNodeImpl<ReportData<T>>(rootReportNode);
		treeTableViewer = new TreeTableViewer<ReportData<T>>(reportTreeTableDataHelper, title, rootNode, exitOnWindowClosing);
		treeTableViewer.getTreeTable().addTreeWillExpandListener(new ReportTreeExpantionListener<T>(reportDataGenerator));
	}
	
	public TreeTableViewer<ReportData<T>> getTreeTableViewer() {
		return treeTableViewer;
	}
}

