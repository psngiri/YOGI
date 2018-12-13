package yogi.report.condition;


public class LongGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Long> {
	public LongGreaterThanOrEqualsCondition(String value) {
		super(value);
	}
	@Override
	public Long scan(String value)
	{
		return Long.valueOf(value);
	}


}
