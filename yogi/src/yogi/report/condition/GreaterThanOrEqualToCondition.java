package yogi.report.condition;

public class GreaterThanOrEqualToCondition<C> extends ConditionBaseImpl<C> {

	public GreaterThanOrEqualToCondition(String value) {
		super(value);
	}

	public boolean satisfied(C data) {
		if(data == null) return true;
		String format = getFormatter().format(data);
		int compareTo = getValue().compareTo(format);
		if(compareTo > 0) return false;
		return true;
	}

}
