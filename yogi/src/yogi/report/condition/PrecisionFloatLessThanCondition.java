package yogi.report.condition;

import yogi.base.util.PrecisionFloat;


public class PrecisionFloatLessThanCondition extends BaseLessThanCondition<PrecisionFloat> {
	public PrecisionFloatLessThanCondition(String value) {
		super(value);
	}

	@Override
	public PrecisionFloat scan(String value)
	{	
		return new PrecisionFloat(Float.valueOf(value),0);
	}


}
