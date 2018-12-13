package yogi.report.condition;



public class FloatNotInCondition extends FloatInCondition {

	public FloatNotInCondition(String value) {
		super(value);
	}
	public FloatNotInCondition(String value,char separator) {
		super(value, separator);
	}
	@Override
	public boolean satisfied(Float data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
