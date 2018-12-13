package yogi.report.condition;


public class LongNotEqualsCondition extends LongComparableCompareCondition {
	public LongNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Long data) {
		return !(compare(data)==0);
	}


}
