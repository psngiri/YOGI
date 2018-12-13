package yogi.report.condition;


public class ShortGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Short> {
	public ShortGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Short scan(String value)
	{
		return Short.valueOf(value);
	}

}
