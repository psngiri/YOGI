package yogi.report.condition;


public abstract class BaseGreaterThanCondition<C extends Comparable<C>> extends BaseComparableCompareCondition<C> {
	public BaseGreaterThanCondition(String value) {
		super(value);
	}
	@Override
	public boolean satisfied(C data) {
		return compare(data)>0;
	}

	@Override
	protected String getSqlCondition() {
		return ">";
	}

}
