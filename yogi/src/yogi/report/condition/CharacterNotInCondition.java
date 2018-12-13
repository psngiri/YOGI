package yogi.report.condition;


public class CharacterNotInCondition extends CharacterInCondition {

	public CharacterNotInCondition(String value) {
		super(value);
	}
	
	public CharacterNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(Character data) {
		return !super.satisfied(data);
	}
	
	@Override
	protected String getSqlCondition() {
		return "!=";
	}
}
