package yogi.report.condition;

import yogi.base.util.PrecisionFloat;


public class PrecisionFloatLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<PrecisionFloat> {
	public PrecisionFloatLessThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public PrecisionFloat scan(String value)
	{	
		return new PrecisionFloat(Float.valueOf(value),0);
	}


}
