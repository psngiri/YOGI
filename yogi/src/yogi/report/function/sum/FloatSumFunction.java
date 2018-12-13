package yogi.report.function.sum;


import yogi.report.function.Function;

public class FloatSumFunction implements Function<Float> {
	float sum;
	public void reset() {
		sum = 0;
	}

	public void process(Float object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
	}

	public Float getValue() {
		return sum;
	}

}
