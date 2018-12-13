package yogi.report.function.average;

import yogi.report.function.Function;

public class DoubleAverageFunction implements Function<Double> {
	private double sum;
	private int count;
	public void reset() {
		sum = 0;
		count = 0;
	}

	public void process(Double object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
		count = count + multiplier;
	}

	public Double getValue() {
		return sum/count;
	}

}
