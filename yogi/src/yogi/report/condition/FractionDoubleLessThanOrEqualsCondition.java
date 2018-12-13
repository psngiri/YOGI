package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<FractionDouble> {
	public FractionDoubleLessThanOrEqualsCondition(String value) {
		super(value);
	}

	public FractionDouble scan(String value)
	{
		return new FractionDouble(Double.valueOf(value),1);
	}

}
