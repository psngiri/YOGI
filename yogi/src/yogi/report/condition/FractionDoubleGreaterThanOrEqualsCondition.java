package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<FractionDouble> {
	public FractionDoubleGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	public FractionDouble scan(String value)
	{
		return new FractionDouble(Double.valueOf(value),1);
	}

}
