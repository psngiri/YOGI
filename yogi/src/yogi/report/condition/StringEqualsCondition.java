package yogi.report.condition;

public class StringEqualsCondition extends BaseComparableCompareCondition<String> {

	public StringEqualsCondition(String value) {
		super(value);
	}

	@Override
	public String scan(String value) {
		return value;
	}
	
	@Override
	public boolean satisfied(String data) {
		return compare(data)==0;
	}

}
