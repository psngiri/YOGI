package yogi.base.util.node;

import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.tree.TreeNode;

public class TreeNodeImpl<T> implements TreeNode {
	Node<T> node;

	public TreeNodeImpl(Node<T> node) {
		this.node = node;
	}

	public Node<T> getNode() {
		return node;
	}

	public TreeNode getChildAt(int childIndex) {
		return createTreeNodeImpl(node.getChildAt(childIndex));
	}

	public int getChildCount() {
		return node.getChildCount();
	}

	public TreeNode getParent() {
		return createTreeNodeImpl(node.getParent());
	}

	public int getIndex(TreeNode candidate) {
		if (!(candidate instanceof TreeNodeImpl<?>))
			return -1;

		@SuppressWarnings("unchecked")
		TreeNodeImpl<T> myNode = (TreeNodeImpl<T>) candidate;
		return this.node.getIndex(myNode.node);
	}

	public boolean getAllowsChildren() {
		return true;
	}

	public boolean isLeaf() {
		return node.isLeaf();
	}

	public Enumeration<? extends TreeNode> children() {
		return new MyEnumeration<T>(this);
	}

	protected TreeNodeImpl<T> createTreeNodeImpl(Node<T> aNode) {
		return new TreeNodeImpl<T>(aNode);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TreeNodeImpl<?> && node == ((TreeNodeImpl<?>) obj).node;
	}

	@Override
	public int hashCode() {
		return node == null ? 0 : node.hashCode();
	}

	private static class MyEnumeration<T> implements Enumeration<TreeNode> {
		Iterator<Node<T>> iterator;
		TreeNodeImpl<T> treeNode;

		MyEnumeration(TreeNodeImpl<T> treeNode) {
			this.treeNode = treeNode;
			this.iterator = treeNode.node.children();
		}

		public boolean hasMoreElements() {
			return iterator.hasNext();
		}

		public TreeNode nextElement() {
			return treeNode.createTreeNodeImpl(iterator.next());
		}
	}
}
