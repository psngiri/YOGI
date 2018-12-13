package yogi.report.viewer.compare;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;

import yogi.base.util.node.Node;
import yogi.report.viewer.ReportTreeNodeImpl;

public class CompareReportTreeExpantionListener<T> implements
		TreeWillExpandListener {
	private CompareReportDataGenerator<T> reportGenerator;
	public CompareReportTreeExpantionListener(CompareReportDataGenerator<T> reportGenerator) {
		super();
		this.reportGenerator = reportGenerator;
	}

	@SuppressWarnings("unchecked")
	public void treeWillExpand(TreeExpansionEvent event)
			throws ExpandVetoException {
		TreePath path = event.getPath();
		ReportTreeNodeImpl<CompareReportData<T>> lastPathComponent = (ReportTreeNodeImpl<CompareReportData<T>>) path.getLastPathComponent();
		Node<CompareReportData<T>> node = lastPathComponent.getNode();
		if(!node.isLeaf()) return;
		int groupIndex = path.getPathCount()-1;
		reportGenerator.generateReportData(groupIndex, node);
	}
	
	public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {
	}
}
