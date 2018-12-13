package yogi.report.condition;


public class IntegerGreaterThanCondition extends BaseGreaterThanCondition<Integer> {
	public IntegerGreaterThanCondition(String value) {
		super(value);
	}
	
	@Override
	public Integer scan(String value)
	{
		return Integer.valueOf(value);
	}

}
