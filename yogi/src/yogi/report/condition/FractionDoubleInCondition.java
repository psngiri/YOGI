package yogi.report.condition;

import yogi.base.util.FractionDouble;
import yogi.base.util.range.Range;

public class FractionDoubleInCondition extends BaseRangeInCondition<FractionDouble,Range<FractionDouble>> {

	public FractionDoubleInCondition(String value) {
		super(value);
	}
	public FractionDoubleInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public FractionDouble scan(String token) {
		return new FractionDouble(Double.valueOf(token),1);
	}
	
	@Override
	protected Range<FractionDouble> getRange(FractionDouble start, FractionDouble end) {
		return new Range<FractionDouble>(start, true, end, true);
	}
}
