package yogi.report.condition;

import yogi.base.util.range.Range;

public class DoubleInCondition extends BaseRangeInCondition<Double,Range<Double>> {

	public DoubleInCondition(String value) {
		super(value);
	}
	public DoubleInCondition(String value,char separator) {
		super(value, separator);
	}

	@Override
	public Double scan(String token) {
		return Double.valueOf(token);
	}
	
	@Override
	protected Range<Double> getRange(Double start, Double end) {
		return new Range<Double>(start, true, end, true);
	}
}
