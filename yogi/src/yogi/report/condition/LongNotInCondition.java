package yogi.report.condition;



public class LongNotInCondition extends LongInCondition {

	public LongNotInCondition(String value) {
		super(value);
	}
	
	public LongNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(Long data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
	
}
