package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class ODServiceTypeLongTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<Long> {

	public ODServiceTypeLongTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public Long parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		return Long.valueOf(new String(fileChannelReader.getCharArray(range.getStart(), range.getEnd(), isTrim())));
	}
}
