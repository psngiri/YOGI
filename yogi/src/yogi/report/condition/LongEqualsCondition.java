package yogi.report.condition;


public class LongEqualsCondition extends LongComparableCompareCondition {
	public LongEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Long data) {
		return compare(data)==0;
	}


}
