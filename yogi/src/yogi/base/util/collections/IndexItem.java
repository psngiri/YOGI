/**
 * 
 */
package yogi.base.util.collections;

import java.io.Serializable;

public class IndexItem<T> implements Comparable<IndexItem<T>>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2586121097017827649L;
	private T item;
	private int index;
	public IndexItem(T item, int index) {
		super();
		this.item = item;
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	public T getItem() {
		return item;
	}
	@Override
	public String toString() {
		return index + " - " + item;
	}
	
	@Override
	public int compareTo(IndexItem<T> o) {
		return index - o.index;
	}
	
}