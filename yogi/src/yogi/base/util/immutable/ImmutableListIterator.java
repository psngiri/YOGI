package yogi.base.util.immutable;

import java.util.List;
import java.util.ListIterator;

public class ImmutableListIterator<E> extends ImmutableIterator<E> implements ListIterator<E> {
	public ImmutableListIterator(List<? extends E> list) {
		super(list);
	}
	
	public ImmutableListIterator(List<? extends E> list, int index) {
		super(list);
		current = index;
	}

	public boolean hasPrevious() {
		return current > 0;
	}

	public E previous() {
		return list.get(--current);
	}

	public int nextIndex() {
		return current;
	}

	public int previousIndex() {
		return current-1;
	}

	public void set(E o) {
		immutable();
	}

	public void add(E o) {
		immutable();
	}

}
