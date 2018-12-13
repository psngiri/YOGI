package yogi.report.condition;


public class ShortEqualsCondition extends ShortComparableCompareCondition {
	public ShortEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Short data) {
		return compare(data)==0;
	}


}
