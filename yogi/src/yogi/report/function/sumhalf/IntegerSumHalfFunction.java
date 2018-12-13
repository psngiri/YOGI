package yogi.report.function.sumhalf;

import yogi.report.function.sum.IntegerSumFunction;

public class IntegerSumHalfFunction extends IntegerSumFunction {

	public Integer getValue() {
		return super.getValue()/2;
	}

}
