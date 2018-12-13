package yogi.report.condition;


public class StringNotInCondition extends StringInCondition {

	public StringNotInCondition(String value) {
		super(value);
	}

	public StringNotInCondition(String value, char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(String data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
