package yogi.report.function.sumhalf;

import yogi.report.function.sum.LongSumFunction;

public class LongSumHalfFunction extends LongSumFunction {
	
	public Long getValue() {
		return super.getValue()/2;
	}

}
