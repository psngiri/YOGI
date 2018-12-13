package yogi.report.condition;

import yogi.base.util.FractionDouble;

public abstract class FractionDoubleComparableCompareCondition extends BaseComparableCompareCondition<FractionDouble> {
	public FractionDoubleComparableCompareCondition(String value) {
		super(value);
	}

	public FractionDouble scan(String value)
	{
		return new FractionDouble(Double.valueOf(value),1);
	}

}
