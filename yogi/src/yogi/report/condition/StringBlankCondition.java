package yogi.report.condition;

public class StringBlankCondition extends IsNullCondition<String> {
	
	public StringBlankCondition(String value) {
		super(value);
	}
	
	@Override
	public boolean satisfied(String data) {
		if(!data.matches("*")) return true;
		return false;
	}

}