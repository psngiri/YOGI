package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryLongEvaluator extends TupleRowBaseEvaluator<Long> {


	public TupleRowBinaryLongEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Long parse(FileChannelReader fileChannelReader) {
		
		try {
			long rtnValue = Util.toInt(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
