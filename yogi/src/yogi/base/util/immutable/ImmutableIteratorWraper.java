package yogi.base.util.immutable;

import java.util.Iterator;

public class ImmutableIteratorWraper<E>  implements Iterator<E>{
	private static final String IMMUTABLE = "immutable";
	private Iterator<E> iterator;
	public ImmutableIteratorWraper(Iterator<E> iterator) {
		super();
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public E next() {
		return iterator.next();
	}

	@Override
	public void remove() {
		throw new RuntimeException(IMMUTABLE);
	}

}
