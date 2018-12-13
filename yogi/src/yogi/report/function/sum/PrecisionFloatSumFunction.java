package yogi.report.function.sum;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.Function;

public class PrecisionFloatSumFunction implements Function<PrecisionFloat> {
	protected float sum;
	protected int precision;
	
	public void reset() {
		sum = 0;
		precision = 0;
	}

	public void process(PrecisionFloat object, int multiplier) {
		sum = sum + object.getValue()*multiplier;
		precision = object.getDecimal();
	}

	public PrecisionFloat getValue() {
		return new PrecisionFloat(sum, precision);
	}
	
}
