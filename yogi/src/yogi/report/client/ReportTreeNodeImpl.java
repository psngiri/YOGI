package yogi.report.client;

import yogi.base.util.node.Node;
import yogi.base.util.node.TreeNodeImpl;
import yogi.report.server.ReportData;

public class ReportTreeNodeImpl<T extends ReportData> extends TreeNodeImpl<T> {

	public ReportTreeNodeImpl(Node<T> node) {
		super(node);
	}

	@Override
	protected TreeNodeImpl<T> createTreeNodeImpl(Node<T> node) {
		return new ReportTreeNodeImpl<T>(node);
	}

	@Override
	public boolean isLeaf() {
		T data = getNode().getData();
		if(data == null) return false;
		return !data.isGroup();
	}

}
