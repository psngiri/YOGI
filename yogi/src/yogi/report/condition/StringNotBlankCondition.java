package yogi.report.condition;

public class StringNotBlankCondition extends StringBlankCondition {
	
	public StringNotBlankCondition(String value) {
		super(value);
	}
	
	@Override
	public boolean satisfied(String data) {
		return !super.satisfied(data);
	}

}