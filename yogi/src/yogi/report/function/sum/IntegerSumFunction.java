package yogi.report.function.sum;

import yogi.report.function.Function;

public class IntegerSumFunction implements Function<Integer> {
	int sum;
	public void reset() {
		sum = 0;
	}

	public void process(Integer object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
	}

	public Integer getValue() {
		return sum;
	}

}
