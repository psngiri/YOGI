package yogi.report.condition;

public class StringNotEqualsCondition  extends BaseComparableCompareCondition<String> {

	public StringNotEqualsCondition(String value) {
		super(value);
	}

	@Override
	public String scan(String value) {
		return value;
	}
	
	@Override
	public boolean satisfied(String data) {
		return compare(data)!=0;
	}

}
