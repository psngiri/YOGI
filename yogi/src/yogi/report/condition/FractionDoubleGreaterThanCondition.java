package yogi.report.condition;

import yogi.base.util.FractionDouble;

public class FractionDoubleGreaterThanCondition extends BaseGreaterThanCondition<FractionDouble> {
	public FractionDoubleGreaterThanCondition(String value) {
		super(value);
	}

	public FractionDouble scan(String value)
	{
		return new FractionDouble(Double.valueOf(value),1);
	}

}
