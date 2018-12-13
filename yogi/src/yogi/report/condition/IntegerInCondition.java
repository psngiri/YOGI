package yogi.report.condition;

import yogi.base.util.range.Range;


public class IntegerInCondition extends BaseRangeInCondition<Integer, Range<Integer>> {

	public IntegerInCondition(String value) {
		super(value);
	}
	public IntegerInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public Integer scan(String token) {
		return Integer.valueOf(token);
	}
	@Override
	protected Range<Integer> getRange(Integer start, Integer end) {
		return new Range<Integer>(start, true, end, true);
	}
	
}
