package yogi.report.condition;


public class FloatEqualsCondition extends FloatComparableCompareCondition {
	public FloatEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Float data) {
		return compare(data)==0;
	}


}
