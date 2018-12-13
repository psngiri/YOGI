package yogi.report.condition;

public class BooleanNotEqualsCondition  extends BaseComparableCompareCondition<Boolean> {

	public BooleanNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public Boolean scan(String value) {
		return Boolean.valueOf(value);
	}
	
	@Override
	public boolean satisfied(Boolean data) {
		return compare(data)!=0;
	}

	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
