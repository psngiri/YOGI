package yogi.base.io;

public class FormatterAdapter<T> implements Formatter<T>{

	public String format(T object) {
		return object.toString();
	}

}
