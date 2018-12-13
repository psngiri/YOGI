package yogi.report.condition;


public class LongLessThanCondition extends BaseLessThanCondition<Long> {
	public LongLessThanCondition(String value) {
		super(value);
	}
	@Override
	public Long scan(String value)
	{
		return Long.valueOf(value);
	}


}
