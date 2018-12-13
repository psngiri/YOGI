package yogi.report.function.sumhalf;

import yogi.report.function.sum.ShortSumFunction;

public class ShortSumHalfFunction extends ShortSumFunction{

	public Short getValue() {
		return (short) (super.getValue()/2);
	}

}
