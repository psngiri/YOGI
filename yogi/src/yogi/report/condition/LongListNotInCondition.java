package yogi.report.condition;

import java.util.List;


public class LongListNotInCondition extends LongListInCondition {

	public LongListNotInCondition(String value) {
		super(value);
	}
	public LongListNotInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public boolean satisfied(List<Long> data) {
		return !super.satisfied(data);
	}
	
	
}
