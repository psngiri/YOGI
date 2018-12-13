package yogi.base.io;

public class ObjectFormatter implements Formatter<Object> {

	@Override
	public String format(Object object) {
		return object.toString();
	}

}
