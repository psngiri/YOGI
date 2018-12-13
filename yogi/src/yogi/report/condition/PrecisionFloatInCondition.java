package yogi.report.condition;

import yogi.base.util.PrecisionFloat;
import yogi.base.util.range.Range;

public class PrecisionFloatInCondition extends BaseRangeInCondition<PrecisionFloat, Range<PrecisionFloat>> {

	public PrecisionFloatInCondition(String value) {
		super(value);
	}

	@Override
	public PrecisionFloat scan(String token) {
		try {
			return new PrecisionFloat( Float.valueOf(token), 0);
		}
		catch(Exception e){
	    	return PrecisionFloat.BLANK;
	    }
	}	

	@Override
	protected Range<PrecisionFloat> getRange(PrecisionFloat start,
			PrecisionFloat end) {
		return new Range<PrecisionFloat>(start, true, end, true);
	}

}
