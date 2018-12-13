package yogi.report.condition;

public class IsNullCondition<C> extends ConditionBaseImpl<C> {
	
	private static final String EMPTY_STRING = "";

	public IsNullCondition(String value) {
		super(value);
	}
	
	public boolean satisfied(C data) {
		if(data == null) return true;
		if(data.equals(EMPTY_STRING)) return true;
		return false;
	}

}