package yogi.report.condition;


public class FloatLessThanCondition extends BaseLessThanCondition<Float> {
	public FloatLessThanCondition(String value) {
		super(value);
	}

	public Float scan(String value)
	{
		return Float.valueOf(value);
	}

}
