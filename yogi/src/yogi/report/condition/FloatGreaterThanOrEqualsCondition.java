package yogi.report.condition;


public class FloatGreaterThanOrEqualsCondition extends BaseGreaterThanOrEqualsCondition<Float> {
	public FloatGreaterThanOrEqualsCondition(String value) {
		super(value);
	}

	public Float scan(String value)
	{
		return Float.valueOf(value);
	}

}
