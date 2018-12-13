package yogi.report.condition;


public class ShortNotEqualsCondition extends ShortComparableCompareCondition {
	public ShortNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Short data) {
		return !(compare(data)==0);
	}


}
