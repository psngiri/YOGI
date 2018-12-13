package yogi.report.function;

public class DummyFunction<R> implements Function<R> {
	private R returnValue;
	
	public R getValue() {
		return returnValue;
	}

	public void process(R object, int multiplier) {
		if (returnValue == null) returnValue = object;
	}

	public void reset() {
		returnValue = null;
	}
}
