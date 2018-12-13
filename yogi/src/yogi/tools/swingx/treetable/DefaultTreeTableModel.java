package yogi.tools.swingx.treetable;

import java.util.List;

import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

import yogi.base.util.node.Node;
import yogi.base.util.node.TreeNodeImpl;


public class DefaultTreeTableModel<T> extends AbstractTreeTableModel {
    private TreeTableDataHelper<T> treeTableDataHelper;

    /**
     * Creates a new {@code DefaultTreeTableModel} with a {@code null} root.
     */
    public DefaultTreeTableModel(TreeTableDataHelper<T> treeTableDataHelper) {
        this(null, treeTableDataHelper);
    }

    /**
     * Creates a new {@code DefaultTreeTableModel} with the specified
     * {@code root}. {@code asksAllowsChildren} is disabled and {@code isLeaf}
     * will provide the same semantics as {@code AbstractTreeTableModel.isLeaf}.
     * 
     * @param root
     *            the root node of the tree
     */
    public DefaultTreeTableModel(TreeNodeImpl<T> root, TreeTableDataHelper<T> treeTableDataHelper) {
        super(root);
        this.treeTableDataHelper = treeTableDataHelper;

    }

    private boolean isValidTreeTableNode(Object node) {
        return node instanceof TreeNodeImpl<?>;
    }

    /**
     * Returns the root of the tree. Returns {@code null} only if the tree has
     * no nodes.
     * 
     * @return the root of the tree
     * 
     * @throws ClassCastException
     *             if {@code root} is not a {@code TreeTableNode}. Even though
     *             subclasses have direct access to {@code root}, they should
     *             avoid accessing it directly.
     * @see AbstractTreeTableModel#root
     * @see #setRoot(TreeTableNode)
     */
    @Override
    public TreeNode getRoot() {
        return (TreeNode) root;
    }

    /**
     * Gets the value for the {@code node} at {@code column}.
     * 
     * @impl delegates to {@code TreeTableNode.getValueAt(int)}
     * @param node
     *            the node whose value is to be queried
     * @param column
     *            the column whose value is to be queried
     * @return the value Object at the specified cell
     * @throws IllegalArgumentException
     *             if {@code node} is not an instance of {@code TreeTableNode}
     *             or is not managed by this model, or {@code column} is not a
     *             valid column index
     */
    @SuppressWarnings("unchecked")
	public Object getValueAt(Object node, int column) {
        if (!isValidTreeTableNode(node)) {
            throw new IllegalArgumentException(
                    "node must be a valid node managed by this model");
        }

        if (column < 0 || column >= getColumnCount()) {
            throw new IllegalArgumentException("column must be a valid index");
        }

        TreeNodeImpl<T> ttn = (TreeNodeImpl) node;

        return treeTableDataHelper.getValueAt(ttn.getNode().getData(), column);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public void setValueAt(Object value, Object node, int column) {
        if (!isValidTreeTableNode(node)) {
            throw new IllegalArgumentException(
                    "node must be a valid node managed by this model");
        }

        if (column < 0 || column >= getColumnCount()) {
            throw new IllegalArgumentException("column must be a valid index");
        }

        TreeNodeImpl<T> ttn = (TreeNodeImpl) node;

            treeTableDataHelper.setValueAt(ttn.getNode().getData(), value, column);

            modelSupport.firePathChanged(new TreePath(getPathToRoot(ttn.getNode())));
        
    }

    /**
     * {@inheritDoc}
     */
    public int getColumnCount() {
        return treeTableDataHelper.getColumnCount();
    }

    /**
     * {@inheritDoc}
     */
    // Can we make getColumnClass final and avoid the complex DTM copy? -- kgs
    @Override
    public String getColumnName(int column) {
        return treeTableDataHelper.getColumnName(column);
    }

    /**
     * {@inheritDoc}
     */
    public Object getChild(Object parent, int index) {
        if (!isValidTreeTableNode(parent)) {
            throw new IllegalArgumentException(
                    "parent must be a TreeTableNode managed by this model");
        }

        return ((TreeNode) parent).getChildAt(index);
    }

    /**
     * {@inheritDoc}
     */
    public int getChildCount(Object parent) {
        if (!isValidTreeTableNode(parent)) {
            throw new IllegalArgumentException(
                    "parent must be a TreeTableNode managed by this model");
        }

        return ((TreeNode) parent).getChildCount();
    }

    /**
     * {@inheritDoc}
     */
    public int getIndexOfChild(Object parent, Object child) {
        if (!isValidTreeTableNode(parent)) {
            throw new IllegalArgumentException(
                    "parent must be a TreeTableNode managed by this model");
        }

        if (!isValidTreeTableNode(parent)) {
            throw new IllegalArgumentException(
                    "child must be a TreeTableNode managed by this model");
        }

        return ((TreeNode) parent).getIndex((TreeNode) child);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	@Override
    public boolean isCellEditable(Object node, int column) {
        if (!isValidTreeTableNode(node)) {
            throw new IllegalArgumentException(
                    "node must be a valid node managed by this model");
        }

        if (column < 0 || column >= getColumnCount()) {
            throw new IllegalArgumentException("column must be a valid index");
        }

        TreeNodeImpl<T> ttn = (TreeNodeImpl) node;

        return treeTableDataHelper.isEditable(ttn.getNode().getData(), column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeaf(Object node) {
        if (!isValidTreeTableNode(node)) {
            throw new IllegalArgumentException(
                    "node must be a TreeTableNode managed by this model");
        }

        return ((TreeNode) node).isLeaf();
    }

    /**
     * Gets the path from the root to the specified node.
     * 
     * @param aNode
     *            the node to query
     * @return an array of {@code TreeTableNode}s, where
     *         {@code arr[0].equals(getRoot())} and
     *         {@code arr[arr.length - 1].equals(aNode)}, or an empty array if
     *         the node is not found.
     * @throws NullPointerException
     *             if {@code aNode} is {@code null}
     */
    public TreeNode[] getPathToRoot(Node<T> aNode) {
    	List<Node<T>> pathFromRoot = aNode.getPathFromRoot();
    	TreeNode[] path = new TreeNode[pathFromRoot.size()];
    	for(int i = 0; i < pathFromRoot.size(); i ++)
    	{
    		path[i] = new TreeNodeImpl<T>(pathFromRoot.get(i));
    	}
        return path;
    }

    /**
     * Sets the root for this table model. If no column identifiers have been
     * specified, this will rebuild the identifier list, using {@code root} as
     * an examplar of the table.
     * 
     * @param root
     *            the node to set as root
     */
    public void setRoot(TreeNodeImpl<T> root) {
        this.root = root;
        modelSupport.fireNewRoot();
    }

    /**
     * Invoked this to insert newChild at location index in parents children.
     * This will then message nodesWereInserted to create the appropriate event.
     * This is the preferred way to add children as it will create the
     * appropriate event.
     */
    public void insertNodeInto(T newChild,
            Node<T> parent, int insertAtIndex) {
    	Node<T> newNode = new Node<T>(parent, newChild, insertAtIndex);

        modelSupport.fireChildAdded(new TreePath(getPathToRoot(parent)), insertAtIndex,
                new TreeNodeImpl<T>(newNode));
    }

    /**
     * Message this to remove node from its parent. This will message
     * nodesWereRemoved to create the appropriate event. This is the preferred
     * way to remove a node as it handles the event creation for you.
     */
    public void removeNodeFromParent(Node<T> node) {
    	Node<T> parent = node.getParent();

        int index = parent.getIndex(node);
        node.delete();

        modelSupport.fireChildRemoved(new TreePath(getPathToRoot(parent)),
                index, new TreeNodeImpl<T>(node));
    }
}
