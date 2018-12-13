package yogi.report.function.sumhalf;


import yogi.base.util.PrecisionFloat;
import yogi.report.function.sum.PrecisionFloatSumFunction;

public class PrecisionFloatSumHalfFunction extends PrecisionFloatSumFunction {
	
	public void process(PrecisionFloat object, int multiplier) {
		sum = sum + object.getValue()*multiplier/2;
		precision = object.getDecimal();
	}

}
