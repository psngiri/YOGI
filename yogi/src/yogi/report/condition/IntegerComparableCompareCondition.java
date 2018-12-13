package yogi.report.condition;



public abstract class IntegerComparableCompareCondition extends BaseComparableCompareCondition<Integer> {
	public IntegerComparableCompareCondition(String value) {
		super(value);
	}

	public Integer scan(String value)
	{
		return Integer.valueOf(value);
	}

}
