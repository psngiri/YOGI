package yogi.report.condition;

import java.util.List;


public class IntegerListNotInCondition extends IntegerListInCondition {

	public IntegerListNotInCondition(String value) {
		super(value);
	}
	public IntegerListNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(List<Integer> data) {
		return !super.satisfied(data);
	}
	
	
}
