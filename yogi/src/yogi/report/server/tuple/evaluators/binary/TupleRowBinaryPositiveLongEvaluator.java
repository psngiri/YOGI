package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowBinaryPositiveLongEvaluator extends TupleRowBinaryLongEvaluator{

	public TupleRowBinaryPositiveLongEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Long parse(FileChannelReader fileChannelReader) {
		Long rtnValue = super.parse(fileChannelReader);
        if(rtnValue <= 0) return null;
		return rtnValue;
	}
	
}