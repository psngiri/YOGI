package yogi.report.condition;



public abstract class FloatComparableCompareCondition extends BaseComparableCompareCondition<Float> {
	public FloatComparableCompareCondition(String value) {
		super(value);
	}

	public Float scan(String value)
	{
		return Float.valueOf(value);
	}

}
