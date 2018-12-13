package yogi.report.condition;

import yogi.base.util.PrecisionFloat;

public class PrecisionFloatEqualsCondition extends PrecisionFloatComparableCompareCondition {
	
	public PrecisionFloatEqualsCondition(String value) {
		super(value);
	}
	
	@Override
	public boolean satisfied(PrecisionFloat data) {
		return compare(data)==0;
		
	}


}
