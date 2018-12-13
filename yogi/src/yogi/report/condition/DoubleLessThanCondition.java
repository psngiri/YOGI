package yogi.report.condition;


public class DoubleLessThanCondition extends BaseLessThanCondition<Double> {
	public DoubleLessThanCondition(String value) {
		super(value);
	}

	public Double scan(String value)
	{
		return Double.valueOf(value);
	}

}
