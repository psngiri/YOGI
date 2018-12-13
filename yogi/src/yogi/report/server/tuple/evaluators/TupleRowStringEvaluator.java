package yogi.report.server.tuple.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowStringEvaluator extends TupleRowBaseEvaluator<String> {

	public TupleRowStringEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader) {
		return new String(fileChannelReader.getCharArray(getRange().getStart(), getRange().getEnd(), isTrim()));
	}
}
