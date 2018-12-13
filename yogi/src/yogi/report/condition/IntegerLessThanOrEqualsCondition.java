package yogi.report.condition;


public class IntegerLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Integer> {
	public IntegerLessThanOrEqualsCondition(String value) {
		super(value);
	}
	
	@Override
	public Integer scan(String value)
	{
		return Integer.valueOf(value);
	}

}
