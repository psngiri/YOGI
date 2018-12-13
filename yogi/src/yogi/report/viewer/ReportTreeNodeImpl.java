package yogi.report.viewer;

import yogi.base.util.node.Node;
import yogi.base.util.node.TreeNodeImpl;

public class ReportTreeNodeImpl<T extends ReportGroupData> extends TreeNodeImpl<T> {

	public ReportTreeNodeImpl(Node<T> node) {
		super(node);
	}

	@Override
	protected TreeNodeImpl<T> createTreeNodeImpl(Node<T> node) {
		return new ReportTreeNodeImpl<T>(node);
	}

	@Override
	public boolean isLeaf() {
		return !getNode().getData().isGroup();
	}

}
