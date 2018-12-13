package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryFloatEvaluator extends TupleRowBaseEvaluator<Float> {

	public TupleRowBinaryFloatEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Float parse(FileChannelReader fileChannelReader) {
		try {
			float rtnValue = Util.toFloat(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
		
	}
}
