package yogi.base.util.immutable;

import java.util.Iterator;
import java.util.List;

public class ImmutableIterator<E> implements Iterator<E> {
	private static String IMMUTABLE = "Immutable";
	protected List<? extends E> list;
	protected int size;
	protected int current = 0;
	
	public ImmutableIterator(List<? extends E> list) {
		super();
		this.list = list;
		size = list.size();
	}

	public boolean hasNext() {
		return current < size;
	}

	public E next() {
		return list.get(current++);
	}

	public void remove() {
		immutable();
	}

	protected void immutable() {
		throw new RuntimeException(IMMUTABLE);
	}
}
