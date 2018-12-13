package yogi.report.condition;


public class ShortLessThanCondition extends BaseLessThanCondition<Short> {
	public ShortLessThanCondition(String value) {
		super(value);
	}

	@Override
	public Short scan(String value)
	{
		return Short.valueOf(value);
	}

}
