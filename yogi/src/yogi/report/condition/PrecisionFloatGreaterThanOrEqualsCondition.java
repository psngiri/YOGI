package yogi.report.condition;

import yogi.base.util.PrecisionFloat;


public class PrecisionFloatGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<PrecisionFloat> {
	public PrecisionFloatGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public PrecisionFloat scan(String value)
	{	
		return new PrecisionFloat(Float.valueOf(value),0);
	}


}
