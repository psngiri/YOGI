package yogi.report.condition;



public abstract class ShortComparableCompareCondition extends BaseComparableCompareCondition<Short> {
	public ShortComparableCompareCondition(String value) {
		super(value);
	}

	public Short scan(String value)
	{
		return Short.valueOf(value);
	}

}
