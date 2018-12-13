package yogi.base.util.node;

import java.util.Comparator;

import yogi.base.util.immutable.ImmutableList;
import yogi.base.util.immutable.Immutables;

public class Nodes {

	public static <T> void sort(Node<T> node, Comparator<T> comparator, boolean propogate)
	{
		NodeComparator<T> nodeComparator = new NodeComparator<T>(comparator);
		sort(node, nodeComparator, propogate);
	}

	private static <T> void sort(Node<T> node, NodeComparator<T> nodeComparator, boolean propogate) {
		if(node.isLeaf()) return;
		ImmutableList<Node<T>> children = node.getChildren();
		Immutables.sort(children, nodeComparator);
		if(!propogate) return;
		for(Node<T> child: children)
		{
			sort(child, nodeComparator, propogate);
		}
	}
	
	static class NodeComparator<T> implements Comparator<Node<T>>
	{
		Comparator<T> comparator;
		public NodeComparator(Comparator<T> comparator) {
			super();
			this.comparator = comparator;
		}
		public int compare(Node<T> o1, Node<T> o2) {
			return comparator.compare(o1.getData(), o2.getData());
		}
	}
}
