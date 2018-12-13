package yogi.report.function.sumhalf;

import yogi.report.function.sum.DoubleSumFunction;

public class DoubleSumHalfFunction extends DoubleSumFunction{

	public Double getValue() {
		return super.getValue()/2;
	}

}
