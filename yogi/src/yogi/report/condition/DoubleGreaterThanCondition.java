package yogi.report.condition;


public class DoubleGreaterThanCondition extends BaseGreaterThanCondition<Double> {
	public DoubleGreaterThanCondition(String value) {
		super(value);
	}

	public Double scan(String value)
	{
		return Double.valueOf(value);
	}

}
