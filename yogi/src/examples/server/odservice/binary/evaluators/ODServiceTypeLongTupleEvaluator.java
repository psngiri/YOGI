package examples.server.odservice.binary.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.binary.Util;
import yogi.report.server.tuple.io.FileChannelReader;

import examples.server.odservice.evaluators.ODServiceTypeBaseTupleEvaluator;

public class ODServiceTypeLongTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<Long> {

	public ODServiceTypeLongTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public Long parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		long rtnValue = Util.toInt(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
		return rtnValue;
	}
}
