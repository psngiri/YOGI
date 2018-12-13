package yogi.report.condition;


public class IntegerGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Integer> {
	public IntegerGreaterThanOrEqualsCondition(String value) {
		super(value);
	}
	
	@Override
	public Integer scan(String value)
	{
		return Integer.valueOf(value);
	}

}
