package yogi.report.client;

import yogi.base.util.node.Node;
import yogi.report.server.Query;
import yogi.report.server.ReportData;
import yogi.tools.viewers.TreeTableViewer;



public class ReportViewer
{
	private TreeTableViewer<ReportData> treeTableViewer;

	public ReportViewer(Query query, String userId, String reportType) {
		this(query, userId, "Report Viewer", false, reportType);
	}
	
	public ReportViewer(Query query, String userId, String title, String reportType) {
		this(query, userId, title, true, reportType);
	}
	
	public ReportViewer(Query query, String userId, String title, boolean exitOnWindowClosing, String reportType) {
		super();
		Node<ReportData> rootReportNode = new Node<ReportData>(null, null);
		ReportDataGenerator reportDataGenerator = new ReportDataGenerator(query, userId, reportType);
		reportDataGenerator.generateReportData(0, rootReportNode);
		ReportTreeNodeImpl<ReportData> rootNode = new ReportTreeNodeImpl<ReportData>(rootReportNode);
		ReportTreeTableDataHelper reportTreeTableDataHelper = new ReportTreeTableDataHelper(reportDataGenerator.getColumnNames());
		treeTableViewer = new TreeTableViewer<ReportData>(reportTreeTableDataHelper, title, rootNode, exitOnWindowClosing);
		treeTableViewer.getTreeTable().addTreeWillExpandListener(new ReportTreeExpantionListener(reportDataGenerator));
	}
	
	public TreeTableViewer<ReportData> getTreeTableViewer() {
		return treeTableViewer;
	}
}

