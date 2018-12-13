package yogi.report.server.tuple.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowDoubleEvaluator extends TupleRowBaseEvaluator<Double> {

	public TupleRowDoubleEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader) {
		try {
			return Double.valueOf(new String(fileChannelReader.getCharArray(getRange().getStart(), getRange().getEnd(), isTrim())));
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
