package yogi.report.condition;


public abstract class BaseGreaterThanOrEqualsCondition<C extends Comparable<C>> extends BaseComparableCompareCondition<C> {
	public BaseGreaterThanOrEqualsCondition(String value) {
		super(value);
	}
	@Override
	public boolean satisfied(C data) {
		return compare(data)>=0;
	}

	@Override
	protected String getSqlCondition() {
		return ">=";
	}

}
