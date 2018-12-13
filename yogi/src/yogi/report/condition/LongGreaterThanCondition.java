package yogi.report.condition;


public class LongGreaterThanCondition extends BaseGreaterThanCondition<Long> {
	public LongGreaterThanCondition(String value) {
		super(value);
	}
	@Override
	public Long scan(String value)
	{
		return Long.valueOf(value);
	}


}
