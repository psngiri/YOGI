package yogi.report.condition;


public class ShortLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Short> {
	public ShortLessThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Short scan(String value)
	{
		return Short.valueOf(value);
	}

}
