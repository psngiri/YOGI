package examples.server.odservice.binary.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

import examples.server.odservice.evaluators.ODServiceTypeBaseTupleEvaluator;

public class ODServiceTypeStringTupleEvaluator extends ODServiceTypeBaseTupleEvaluator<String> {

	public ODServiceTypeStringTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public String parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		String rtnValue = new String(fileChannelReader.getByteArray(getRange().getStart(), getRange().getEnd()));
		if(trim){
			rtnValue = rtnValue.trim();
		}
		return rtnValue;
	}
}
