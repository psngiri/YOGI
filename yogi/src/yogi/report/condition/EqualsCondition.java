package yogi.report.condition;

public class EqualsCondition<C> extends ConditionBaseImpl<C> {

	public EqualsCondition(String value) {
		super(value);
	}

	public boolean satisfied(C data) {
		if(data == null) return true;
		return getValue().equals(getFormatter().format(data));
	}

}
