package yogi.report.condition;



public abstract class DoubleComparableCompareCondition extends BaseComparableCompareCondition<Double> {
	public DoubleComparableCompareCondition(String value) {
		super(value);
	}

	public Double scan(String value)
	{
		return Double.valueOf(value);
	}

}
