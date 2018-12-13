package yogi.report.condition;

import yogi.base.util.range.Range;


public class FloatInCondition extends BaseRangeInCondition<Float, Range<Float>> {

	public FloatInCondition(String value) {
		super(value);
	}
	public FloatInCondition(String value,char separator) {
		super(value, separator);
	}


	@Override
	public Float scan(String token) {
		return Float.valueOf(token);
	}
	
	
	@Override
	protected Range<Float> getRange(Float start, Float end) {
		return new Range<Float>(start, true, end, true);
	}
	
}
