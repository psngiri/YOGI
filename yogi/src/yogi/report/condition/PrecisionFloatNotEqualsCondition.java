package yogi.report.condition;

import yogi.base.util.PrecisionFloat;


public class PrecisionFloatNotEqualsCondition extends PrecisionFloatComparableCompareCondition {
	public PrecisionFloatNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(PrecisionFloat data) {
		return !(compare(data)==0);
	}


}
