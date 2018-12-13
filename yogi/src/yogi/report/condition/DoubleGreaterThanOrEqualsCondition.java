package yogi.report.condition;


public class DoubleGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Double> {
	public DoubleGreaterThanOrEqualsCondition(String value) {
		super(value);
	}
	
	public Double scan(String value)
	{
		return Double.valueOf(value);
	}

}
