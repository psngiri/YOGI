package yogi.base.util.node.test;

import yogi.base.util.node.Node;
import yogi.base.util.node.TreeNodeImpl;
import junit.framework.TestCase;

public class TreeNodeImplTest extends TestCase {
	public void testTreeNodesAreEqualIfTheirEnclosedNodesAreTheSame() {
		Node<String> firstNode = new Node<String>(null, "boo");
		Node<String> secondNode = new Node<String>(null, "boo");
		TreeNodeImpl<String> firstTreeNode = new TreeNodeImpl<String>(firstNode);
		TreeNodeImpl<String> secondTreeNode = new TreeNodeImpl<String>(secondNode);
		TreeNodeImpl<String> thirdTreeNode = new TreeNodeImpl<String>(firstNode);

		assertEquals(firstTreeNode, thirdTreeNode);
		assertFalse(firstTreeNode.equals(secondTreeNode));
	}
}
