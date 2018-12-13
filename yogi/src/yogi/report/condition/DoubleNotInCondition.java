package yogi.report.condition;



public class DoubleNotInCondition extends DoubleInCondition {

	public DoubleNotInCondition(String value) {
		super(value);
	}

	public DoubleNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(Double data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
