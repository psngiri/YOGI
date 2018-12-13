package yogi.report.condition;

import yogi.base.util.range.Range;


public class LongInCondition extends BaseRangeInCondition<Long, Range<Long>> {

	public LongInCondition(String value) {
		super(value);
	}
	public LongInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public Long scan(String token) {
		return Long.valueOf(token);
	}
	@Override
	protected Range<Long> getRange(Long start, Long end) {
		return new Range<Long>(start, true, end, true);
	}
	
}
