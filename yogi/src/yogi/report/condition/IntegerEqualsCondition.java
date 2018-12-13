package yogi.report.condition;


public class IntegerEqualsCondition extends IntegerComparableCompareCondition {
	public IntegerEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Integer data) {
		return compare(data)==0;
	}


}
