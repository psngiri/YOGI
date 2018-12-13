package yogi.report.condition;


public class DoubleEqualsCondition extends DoubleComparableCompareCondition {
	public DoubleEqualsCondition(String value) {
		super(value);
	}

	@Override
	public boolean satisfied(Double data) {
		return compare(data)==0;
	}


}
