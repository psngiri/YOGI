package yogi.base.util;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {
	private static String IMMUTABLE = "Immutable";
	protected E[] array;
	protected int size;
	protected int current = 0;
	
	public ArrayIterator(E[] array) {
		super();
		this.array = array;
		size = array.length;
	}
		
	protected void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public boolean hasNext() {
		return current < size;
	}

	public E next() {
		return array[current++];
	}

	public void remove() {
		immutable();
	}

	protected void immutable() {
		throw new RuntimeException(IMMUTABLE);
	}
}
