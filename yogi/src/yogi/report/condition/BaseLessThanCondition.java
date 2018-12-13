package yogi.report.condition;


public abstract class BaseLessThanCondition<C extends Comparable<C>> extends BaseComparableCompareCondition<C> {
	public BaseLessThanCondition(String value) {
		super(value);
	}
	@Override
	public boolean satisfied(C data) {
		return compare(data)<0;
	}

	@Override
	protected String getSqlCondition() {
		return "<";
	}

}
