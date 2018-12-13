package yogi.report.condition;

public class LessThanOrEqualToNumberCondition<C extends Number> extends ConditionBaseImpl<C> {
	double doubleValue;
	public LessThanOrEqualToNumberCondition(String value) {
		super(value);
		doubleValue = Double.parseDouble(value);
	}

	public boolean satisfied(C data) {
		if(data == null) return true;
		return data.doubleValue() <= doubleValue;
	}

}