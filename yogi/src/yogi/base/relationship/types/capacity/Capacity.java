package yogi.base.relationship.types.capacity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yogi.base.util.node.Node;
import yogi.base.util.range.Range;

public class Capacity {
	private int initialCapacity;
	private int capacity;
	private Class<?> klass;
	private Range<Integer> range;
	private int lastIndexUsed = -1;
	private Set<Integer> indexesUsedOutsideRange;
	private Node<Capacity> node;
	
	public Capacity(Node<Capacity> node, Class<?> klass) {
		super();
		this.klass = klass;
		this.node = node;
	}
	
	protected Capacity(Node<Capacity> node, Class<?> klass,int startIndex, int initialCapacity) {
		this(node, klass);
		this.initialCapacity = initialCapacity;
		calculateRange(startIndex);
	}

	private void calculateRange(int startIndex) {
		int endIndex = startIndex + initialCapacity -1;
		this.range = new Range<Integer>(startIndex, endIndex);
	}

	public int getStartIndex()
	{
		if(node.getParent() == null) return 0;
 		return node.getParent().getData().getEndIndex() + 1;
	}
	
	public int getEndIndex() {
		return getStartIndex() + capacity -1;
	}

	
	public int getCapacity() {
		return capacity;
	}
	public int getInitialCapacity() {
		return initialCapacity;
	}
	public Class<?> getKlass() {
		return klass;
	}

	public Range<Integer> getRange() {
		return range;
	}
	public int getNextIndex()
	{
		capacity ++;
		return computeNext();
		
	}

	private int computeNext() {
		if(range != null)
		{
			if(lastIndexUsed == -1)
			{
				lastIndexUsed = range.getStart();
				return lastIndexUsed;
			}
			if(lastIndexUsed <= range.getEnd())
			{
				lastIndexUsed = lastIndexUsed + 1;
				return lastIndexUsed;
			}
		}
		return computeNextIndex();
	}

	private Set<Integer> getIndexesUsedOutsideRange() {
		if(indexesUsedOutsideRange == null) indexesUsedOutsideRange = new HashSet<Integer>();
		return indexesUsedOutsideRange;
	}
	boolean contains(int index)
	{
		if(getIndexesUsedOutsideRange() == null) return false;
		return getIndexesUsedOutsideRange().contains(index);
	}
	
	private int computeNextIndex()
	{
		int index = lastIndexUsed;
		List<Node<Capacity>> decendents = node.getDecendents();
		for(Node<Capacity> leaf: decendents)
		{
			int endIndex = -1;
			Range<Integer> myRange = leaf.getData().getRange();
			if(myRange != null) endIndex = myRange.getEnd();
			if(index < endIndex) index = endIndex;
		}
		while(true)
		{
			index ++;
			if(contains(index)) continue;
			if(checkContainsInLeaves(index, decendents)) continue;
			if(checkContainsInParents(index)) continue;
			break;
		}
		getIndexesUsedOutsideRange().add(index);
		return index;
	}

	private boolean checkContainsInParents(int index) {
		Node<Capacity> myNode = node;
		while(myNode != null)
		{
			myNode = myNode.getParent();
			if(myNode == null) return false;
			if(myNode.getData().contains(index)) return true;
		}
		return false;
	}

	private boolean checkContainsInLeaves(int index, List<Node<Capacity>> leaves) {
		for(Node<Capacity> leaf: leaves)
		{
			if(leaf.getData().contains(index)) return true;
		}
		return false;
	}

}
