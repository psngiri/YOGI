package yogi.report.function.sumhalf;

import yogi.report.function.sum.FloatSumFunction;

public class FloatSumHalfFunction extends FloatSumFunction{

	public Float getValue() {
		return super.getValue()/2;
	}

}
