package yogi.report.condition;


public class FloatNotEqualsCondition extends FloatComparableCompareCondition {
	public FloatNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Float data) {
		return !(compare(data)==0);
	}


}
