package yogi.report.function.average;

import yogi.report.function.Function;

public class FloatAverageFunction implements Function<Float> {
	private float sum;
	private int count;
	public void reset() {
		sum = 0;
		count = 0;
	}

	public void process(Float object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
		count = count + multiplier;
	}

	public Float getValue() {
		return sum/count;
	}

}
