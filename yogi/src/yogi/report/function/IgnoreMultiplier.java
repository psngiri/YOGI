package yogi.report.function;

public class IgnoreMultiplier<R> implements Function<R> {
	private Function<R> function;

	public IgnoreMultiplier(Function<R> function) {
		super();
		this.function = function;
	}

	public void reset() {
		function.reset();
	}

	public void process(R object, int multiplier) {
		function.process(object, 1);
	}

	public R getValue() {
		return function.getValue();
	}

}
