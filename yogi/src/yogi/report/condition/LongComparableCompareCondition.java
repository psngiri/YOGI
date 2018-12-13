package yogi.report.condition;



public abstract class LongComparableCompareCondition extends BaseComparableCompareCondition<Long> {
	public LongComparableCompareCondition(String value) {
		super(value);
	}

	public Long scan(String value)
	{
		return Long.valueOf(value);
	}

}
