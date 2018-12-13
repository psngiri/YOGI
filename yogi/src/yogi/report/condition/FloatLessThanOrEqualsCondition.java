package yogi.report.condition;


public class FloatLessThanOrEqualsCondition extends BaseLessThanOrEqualsCondition<Float> {
	public FloatLessThanOrEqualsCondition(String value) {
		super(value);
	}

	public Float scan(String value)
	{
		return Float.valueOf(value);
	}

}
