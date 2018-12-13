package yogi.report.condition;

import yogi.base.util.PrecisionFloat;



public abstract class PrecisionFloatComparableCompareCondition extends BaseComparableCompareCondition<PrecisionFloat> {
	public PrecisionFloatComparableCompareCondition(String value) {
		super(value);
	}

	public PrecisionFloat scan(String value)
	{
		
		return new PrecisionFloat(Float.valueOf(value),0);
	}

}
