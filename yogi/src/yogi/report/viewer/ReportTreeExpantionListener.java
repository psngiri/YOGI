package yogi.report.viewer;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import yogi.base.util.node.Node;

public class ReportTreeExpantionListener<T> implements
		TreeWillExpandListener {
	private ReportDataGenerator<T> reportGenerator;
	public ReportTreeExpantionListener(ReportDataGenerator<T> reportGenerator) {
		super();
		this.reportGenerator = reportGenerator;
	}

	@SuppressWarnings("unchecked")
	public void treeWillExpand(TreeExpansionEvent event)
			throws ExpandVetoException {
		TreePath path = event.getPath();
		ReportTreeNodeImpl<ReportData<T>> lastPathComponent = (ReportTreeNodeImpl<ReportData<T>>) path.getLastPathComponent();
		Node<ReportData<T>> node = lastPathComponent.getNode();
		if(!node.isLeaf()) return;
		int groupIndex = path.getPathCount()-1;
		reportGenerator.generateReportData(groupIndex, node);
	}
	
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
	}
}
