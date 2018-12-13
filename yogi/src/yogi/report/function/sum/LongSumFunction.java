package yogi.report.function.sum;

import yogi.report.function.Function;

public class LongSumFunction implements Function<Long> {
	long sum;
	public void reset() {
		sum = 0;
	}

	public void process(Long object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
	}

	public Long getValue() {
		return sum;
	}

}
