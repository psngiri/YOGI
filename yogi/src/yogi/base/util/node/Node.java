package yogi.base.util.node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;

public class Node<T> {
	private Node<T> parent;
	private List<Node<T>> children;
	private T data;
	
	public Node(Node<T> parent)
	{
		super();
		this.parent = parent;
		if(parent != null) parent.add(this);
	}
	
	public Node(Node<T> parent, T data) {
		this(parent);
		setData(data);
	}

	public Node(Node<T> parent, T data, int insertAtIndex) {
		this.parent = parent;
		if(parent != null) parent.add(this, insertAtIndex);
		setData(data);
	}
	
	protected void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	protected void add(Node<T> child)
	{
		if(children == null) children = new ArrayList<Node<T>>();
		children.add(child);
	}
	
	protected void add(Node<T> child, int insertAtIndex)
	{
		if(children == null) children = new ArrayList<Node<T>>();
		children.add(insertAtIndex, child);
	}
	
	public Iterator<Node<T>> children() {
		if(children == null) return new ArrayList<Node<T>>().iterator();
		return children.iterator();
	}
	
	public Node<T> getParent() {
		return parent;
	}
	
	public Node<T> getChildAt(int index)
	{
		if(children == null) return null;
		return children.get(index);
	}
	
	public int getChildCount()
	{
		if(children == null) return 0;
		return children.size();
	}
	
	public int getIndex(Node<T> child)
	{
		return children.indexOf(child);
	}
	
	public boolean isLeaf()
	{
		if(children == null) return true;
		return children.isEmpty();
	}
	
	public boolean isRoot()
	{
		if(parent == null) return true;
		return false;
	}
	
	public List<Node<T>> getPathToRoot()
	{
		List<Node<T>> rtnValue = new ArrayList<Node<T>>();
		Node<T> currentNode = this;
		while(!currentNode.isRoot())
		{
			rtnValue.add(currentNode);
			currentNode = currentNode.getParent();
		}
		rtnValue.add(currentNode.getParent());
		return rtnValue;
	}
	
	public Node<T> getRoot()
	{
		Node<T> currentNode = this;
		while(!currentNode.isRoot())
		{
			currentNode = currentNode.getParent();
		}
		return currentNode;
	}
	
	public List<Node<T>> getPathFromRoot()
	{
		List<Node<T>> pathToRoot = getPathToRoot();
		List<Node<T>> rtnValue = new ArrayList<Node<T>>(pathToRoot.size());
		for(int i = pathToRoot.size()-1; i >= 0; i--)
		{
			rtnValue.add(pathToRoot.get(i));
		}
		return rtnValue;
	}
	
	public void delete()
	{
		if(isRoot()) return;
		parent.children.remove(this);
	}
	
	public List<Node<T>> getDecendents()
	{
		List<Node<T>> rtnValue = new ArrayList<Node<T>>();
		if(children == null) return rtnValue;
		for(Node<T> node:new ImmutableList<Node<T>>(children))
		{
			rtnValue.add(node);
			rtnValue.addAll(node.getDecendents());
		}
		return rtnValue;
	}

	public ImmutableList<Node<T>> getChildren()
	{
		List<Node<T>> myChildren = children;
		if(children == null) myChildren = new ArrayList<Node<T>>();
		return new ImmutableList<Node<T>>(myChildren);
	}
}
