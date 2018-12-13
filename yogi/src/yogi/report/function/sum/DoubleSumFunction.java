package yogi.report.function.sum;


import yogi.report.function.Function;

public class DoubleSumFunction implements Function<Double> {
	double sum;
	public void reset() {
		sum = 0;
	}

	public void process(Double object, int multiplier) {
		if(object == null) return;
		sum = sum + object*multiplier;
	}

	public Double getValue() {
		return sum;
	}

}
