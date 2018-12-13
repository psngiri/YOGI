package yogi.report.condition;


public class IntegerLessThanCondition extends BaseLessThanCondition<Integer> {
	public IntegerLessThanCondition(String value) {
		super(value);
	}
	
	@Override
	public Integer scan(String value)
	{
		return Integer.valueOf(value);
	}

}
