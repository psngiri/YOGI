package yogi.report.function.average;

import yogi.report.function.Function;

public class LongAverageFunction implements Function<Long> {
	private long sum;
	private int count;
	public void reset() {
		sum = 0;
		count = 0;
	}

	public void process(Long object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
		count = count +  multiplier;
	}

	public Long getValue() {
		return sum/count;
	}

}
