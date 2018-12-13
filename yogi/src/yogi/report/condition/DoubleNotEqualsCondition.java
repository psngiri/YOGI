package yogi.report.condition;


public class DoubleNotEqualsCondition extends DoubleComparableCompareCondition {
	public DoubleNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Double data) {
		return !(compare(data)==0);
	}


}
