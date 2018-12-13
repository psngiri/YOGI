package yogi.base;

public class DefaultSelector<T> implements Selector<T> {

	boolean defaultValue;
	
	public DefaultSelector(boolean defaultValue) {
		super();		
		this.defaultValue = defaultValue;
	}

	public boolean select(T item) {
		return defaultValue;
	}

}
