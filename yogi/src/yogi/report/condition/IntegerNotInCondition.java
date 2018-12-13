package yogi.report.condition;



public class IntegerNotInCondition extends IntegerInCondition {

	public IntegerNotInCondition(String value) {
		super(value);
	}
	
	public IntegerNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(Integer data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
