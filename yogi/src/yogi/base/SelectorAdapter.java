package yogi.base;

public class SelectorAdapter<T> implements Selector<T> {

	public boolean select(T item) {
		return true;
	}

}
