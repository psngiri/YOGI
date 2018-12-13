package yogi.report.client;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import yogi.base.util.node.Node;
import yogi.report.server.ReportData;

public class ReportTreeExpantionListener implements
		TreeWillExpandListener {
	private ReportDataGenerator reportGenerator;
	public ReportTreeExpantionListener(ReportDataGenerator reportGenerator) {
		super();
		this.reportGenerator = reportGenerator;
	}

	@SuppressWarnings("unchecked")
	public void treeWillExpand(TreeExpansionEvent event)
			throws ExpandVetoException {
		TreePath path = event.getPath();
		ReportTreeNodeImpl<ReportData> lastPathComponent = (ReportTreeNodeImpl<ReportData>) path.getLastPathComponent();
		Node<ReportData> node = lastPathComponent.getNode();
		if(!node.isLeaf()) return;
		int groupIndex = path.getPathCount()-1;
		reportGenerator.generateReportData(groupIndex, node);
	}
	
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
	}
}
