package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceTypeStringTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<String> {

	public ODServiceTypeStringTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public String parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		return new String(fileChannelReader.getCharArray(range.getStart(), range.getEnd(), isTrim()));
	}
}
