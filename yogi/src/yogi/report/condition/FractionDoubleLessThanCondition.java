package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleLessThanCondition extends BaseLessThanCondition<FractionDouble> {
	public FractionDoubleLessThanCondition(String value) {
		super(value);
	}

	public FractionDouble scan(String value)
	{
		return new FractionDouble(Double.valueOf(value),1);
	}

}
