package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleNotInCondition extends FractionDoubleInCondition {

	public FractionDoubleNotInCondition(String value) {
		super(value);
	}
	public FractionDoubleNotInCondition(String value,char separator) {
		super(value, separator);
	}

	public boolean satisfied(FractionDouble data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
