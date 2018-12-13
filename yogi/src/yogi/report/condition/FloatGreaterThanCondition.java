package yogi.report.condition;


public class FloatGreaterThanCondition extends BaseGreaterThanCondition<Float> {
	public FloatGreaterThanCondition(String value) {
		super(value);
	}

	public Float scan(String value)
	{
		return Float.valueOf(value);
	}

}
