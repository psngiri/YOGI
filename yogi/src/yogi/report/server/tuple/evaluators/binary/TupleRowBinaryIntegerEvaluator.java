package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryIntegerEvaluator extends TupleRowBaseEvaluator<Integer> {

	public TupleRowBinaryIntegerEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Integer parse(FileChannelReader fileChannelReader) {
		try {
			int rtnValue = Util.toInt(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
