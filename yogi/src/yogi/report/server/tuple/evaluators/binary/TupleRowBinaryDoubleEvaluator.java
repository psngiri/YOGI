package yogi.report.server.tuple.evaluators.binary;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowBinaryDoubleEvaluator extends TupleRowBaseEvaluator<Double> {

	public TupleRowBinaryDoubleEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader) {
		
		try {
			double rtnValue = Util.toFloat(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
		
	}
}
