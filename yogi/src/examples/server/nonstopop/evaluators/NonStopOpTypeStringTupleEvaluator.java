package examples.server.nonstopop.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class NonStopOpTypeStringTupleEvaluator extends NonStopOpTypeBaseTupleEvaluator<String> {

	public NonStopOpTypeStringTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public String parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		return new String(fileChannelReader.getCharArray(range.getStart(), range.getEnd(), isTrim()));
	}
}
