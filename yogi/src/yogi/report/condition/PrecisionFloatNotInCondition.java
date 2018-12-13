package yogi.report.condition;

import java.util.HashSet;

import yogi.base.util.PrecisionFloat;


public class PrecisionFloatNotInCondition extends PrecisionFloatInCondition {
	HashSet<Float> values = new HashSet<Float>();
	
	public PrecisionFloatNotInCondition(String value) {
		super(value);
	}
	
	@Override
	public boolean satisfied(PrecisionFloat data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}


