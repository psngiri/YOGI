package yogi.report.condition;

public class BooleanEqualsCondition extends BaseComparableCompareCondition<Boolean> {

	public BooleanEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Boolean scan(String value) {
		return Boolean.valueOf(value);
	}
	
	@Override
	public boolean satisfied(Boolean data) {
		return compare(data)==0;
	}

	@Override
	protected String getSqlCondition() {
		return "=";
	}
}
