package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceTypeDoubleTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<Double> {

	public ODServiceTypeDoubleTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public Double parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		return Double.valueOf(new String(fileChannelReader.getCharArray(range.getStart(), range.getEnd(), isTrim())));
	}
}
