package yogi.report.function;

public interface Function<R> {
	void reset();
	void process(R object, int multiplier);
	R getValue();
}
