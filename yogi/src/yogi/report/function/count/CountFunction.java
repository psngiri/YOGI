package yogi.report.function.count;

import yogi.report.function.Function;

public class CountFunction implements Function<Integer> {
	int count;
	public void reset() {
		count = 0;
	}

	public void process(Integer object, int multiplier) {
		count = count + multiplier;
	}

	public Integer getValue() {
		return count;
	}

}
