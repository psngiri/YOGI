package yogi.base.util.collections;

import java.util.ArrayList;
import java.util.Collection;

public class CyclicalList<T> extends ArrayList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109070510491103442L;

	public CyclicalList() {
		super();
	}

	public CyclicalList(Collection<T> c) {
		super(c);
	}

	public CyclicalList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public IndexItem<T> getIndexItem(int index)
	{
		return new IndexItem<T>(get(index), index);
	}
	
	public IndexItem<T> getNextIndexItem(IndexItem<T> indexItem)
	{
		int index = indexItem.getIndex() + 1;
		if(index == size()) index = 0;
		return new IndexItem<T>(get(index), index);
	}

	public IndexItem<T> getPreviousIndexItem(IndexItem<T> indexItem)
	{
		int index = indexItem.getIndex() - 1;
		if(index == -1) index = size() -1;
		return new IndexItem<T>(get(index), index);
	}
	
	public IndexItem<T> getFirstItem()
	{
		return getIndexItem(0);
	}
	
	public IndexItem<T> getLastItem()
	{
		return getIndexItem(size() -1);
	}
}
