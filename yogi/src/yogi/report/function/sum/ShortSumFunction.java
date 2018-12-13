package yogi.report.function.sum;


import yogi.report.function.Function;

public class ShortSumFunction implements Function<Short> {
	short sum;
	public void reset() {
		sum = 0;
	}

	public Short getValue() {
		return sum;
	}

	@Override
	public void process(Short object, int multiplier) {
		if(object == null) return;
		sum = (short) (sum + object*multiplier);
	}

}
