package yogi.report.condition;


public class ShortGreaterThanCondition extends BaseGreaterThanCondition<Short> {
	public ShortGreaterThanCondition(String value) {
		super(value);
	}

	@Override
	public Short scan(String value)
	{
		return Short.valueOf(value);
	}

}
