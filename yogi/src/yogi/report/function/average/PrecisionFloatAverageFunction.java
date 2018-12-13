package yogi.report.function.average;

import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;

public class PrecisionFloatAverageFunction implements Function<PrecisionFloat> {
	private float sum;
	private int count;
	private int precision;
	
	public void reset() {
		sum = 0;
		count = 0;
		precision = 0;
	}

	public void process(PrecisionFloat object, int multiplier) {
		sum = sum + object.getValue()*multiplier;
		count = count + multiplier;
		precision = object.getDecimal();
	}

	public PrecisionFloat getValue() {
		return new PrecisionFloat(sum/count, precision);
	}

}
