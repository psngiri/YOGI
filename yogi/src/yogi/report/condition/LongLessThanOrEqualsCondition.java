package yogi.report.condition;


public class LongLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Long> {
	public LongLessThanOrEqualsCondition(String value) {
		super(value);
	}
	@Override
	public Long scan(String value)
	{
		return Long.valueOf(value);
	}


}
