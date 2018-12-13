package yogi.report.server.tuple.evaluators.complex;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowLongEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowFlightNumberEvaluator extends TupleRowLongEvaluator{

	public TupleRowFlightNumberEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Long parse(FileChannelReader fileChannelReader) {
		Long rtnValue = super.parse(fileChannelReader);
        if(rtnValue == 0) return null;
		return rtnValue;
	}
	
}