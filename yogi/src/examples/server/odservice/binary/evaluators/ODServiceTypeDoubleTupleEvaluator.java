package examples.server.odservice.binary.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.evaluators.binary.Util;
import yogi.report.server.tuple.io.FileChannelReader;

import examples.server.odservice.evaluators.ODServiceTypeBaseTupleEvaluator;

public class ODServiceTypeDoubleTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<Double> {

	public ODServiceTypeDoubleTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public Double parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		double rtnValue = Util.toFloat(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()), 0);
		return rtnValue;
	}
}
