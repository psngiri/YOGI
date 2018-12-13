package yogi.report.condition;

public class IsNotNullCondition<C> extends IsNullCondition<C> {
	
	public IsNotNullCondition(String value) {
		super(value);
	}
	
	public boolean satisfied(C data) {
		return !super.satisfied(data);
	}

}
