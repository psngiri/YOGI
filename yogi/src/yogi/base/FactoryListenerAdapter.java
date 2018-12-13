package yogi.base;

public class FactoryListenerAdapter<T> implements FactoryListener<T> {

	public void add(T object) {
	}

	public boolean delete(T object) {
		return true;
	}

	public boolean clearAll() {
		return true;
	}

}
