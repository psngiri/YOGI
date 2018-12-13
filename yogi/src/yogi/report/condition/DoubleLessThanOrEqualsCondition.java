package yogi.report.condition;


public class DoubleLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Double> {
	public DoubleLessThanOrEqualsCondition(String value) {
		super(value);
	}

	public Double scan(String value)
	{
		return Double.valueOf(value);
	}

}
