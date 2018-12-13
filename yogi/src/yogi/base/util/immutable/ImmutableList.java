package yogi.base.util.immutable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ImmutableList<E> implements List<E> {
	private static String IMMUTABLE = "Immutable";
	private List<? extends E> wrapped;
	
	public ImmutableList(List<? extends E> list) {
		super();
		this.wrapped = list;
		if(list == null) throw new NullPointerException(IMMUTABLE);
//		if(list instanceof ImmutableList) throw new RuntimeException(IMMUTABLE);
	}
	
	@SuppressWarnings("unchecked")
	List<E> getList()
	{
		return (List<E>) wrapped;
	}
	
	public int size() {
		return wrapped.size();
	}

	public boolean isEmpty() {
		return wrapped.isEmpty();
	}

	public boolean contains(Object o) {
		return wrapped.contains(o);
	}

	public Iterator<E> iterator() {
		return new ImmutableIterator<E>(wrapped);
	}

	public Object[] toArray() {
		return wrapped.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return wrapped.toArray(a);
	}

	public boolean add(E o) {
		throw new RuntimeException(IMMUTABLE);
	}

	public boolean remove(Object o) {
		throw new RuntimeException(IMMUTABLE);
	}

	public boolean containsAll(Collection<?> c) {
		return wrapped.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		throw new RuntimeException(IMMUTABLE);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		throw new RuntimeException(IMMUTABLE);
	}

	public boolean removeAll(Collection<?> c) {
		throw new RuntimeException(IMMUTABLE);
	}

	public boolean retainAll(Collection<?> c) {
		throw new RuntimeException(IMMUTABLE);
	}

	public void clear() {
		throw new RuntimeException(IMMUTABLE);
	}

	public E get(int index) {
		return wrapped.get(index);
	}

	public E set(int index, E element) {
		throw new RuntimeException(IMMUTABLE);
	}

	public void add(int index, E element) {
		throw new RuntimeException(IMMUTABLE);
	}

	public E remove(int index) {
		throw new RuntimeException(IMMUTABLE);
	}

	public int indexOf(Object o) {
		return wrapped.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return wrapped.lastIndexOf(o);
	}

	public ImmutableListIterator<E> listIterator() {
		return new ImmutableListIterator<E>(wrapped);
	}

	public ImmutableListIterator<E> listIterator(int index) {
		return new ImmutableListIterator<E>(wrapped, index);
	}

	public ImmutableList<E> subList(int fromIndex, int toIndex) {
		return new ImmutableList<E>(wrapped.subList(fromIndex, toIndex));
	}

	@Override
	public String toString() {
		return wrapped.toString();
	}
	
	@Override
	public int hashCode() {
		return wrapped.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that)
			return true;
		if (!(that instanceof List<?>))
			return false;
		List<?> other = (List<?>) that;
		return wrapped.equals(other);
	}
	
	public E getLast() {
		if(this.isEmpty()) return null;
		return this.get(this.size() - 1);
	}
}
