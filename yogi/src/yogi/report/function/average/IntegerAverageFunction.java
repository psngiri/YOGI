package yogi.report.function.average;

import yogi.report.function.Function;

public class IntegerAverageFunction implements Function<Integer> {
	private int sum;
	private int count;
	public void reset() {
		sum = 0;
		count = 0;
	}

	public void process(Integer object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
		count = count + multiplier;
	}

	public Integer getValue() {
		return sum/count;
	}

}
