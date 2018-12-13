package yogi.base;

import java.util.Comparator;
import java.util.List;

import yogi.base.io.ObjectWriter;

public class SynchronizedFactory<T> extends Factory<T>{

	public SynchronizedFactory(Manager<T> manager) {
		super(manager);
	}

	@Override
	protected synchronized List<T> getObjects() {
		return super.getObjects();
	}

	@Override
	protected synchronized void add(T object) {
		super.add(object);
	}

	@Override
	public synchronized void clear() {
		super.clear();
	}

	@Override
	public synchronized void delete(T object) {
		super.delete(object);
	}

	@Override
	public synchronized boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public synchronized void write(ObjectWriter<T> writer, Selector<? super T> selector, Comparator<T> comparator) {
		super.write(writer, selector, comparator);
	}

	@Override
	public synchronized void purge(Selector<T> selector) {
		super.purge(selector);
	}

}
