package yogi.report.condition;


public class IntegerNotEqualsCondition extends IntegerComparableCompareCondition {
	public IntegerNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Integer data) {
		return !(compare(data)==0);
	}


}
