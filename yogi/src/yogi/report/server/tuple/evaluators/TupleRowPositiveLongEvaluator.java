package yogi.report.server.tuple.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowPositiveLongEvaluator extends TupleRowBaseEvaluator<Long> {


	public TupleRowPositiveLongEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Long parse(FileChannelReader fileChannelReader) {
		try {
			String value = new String(fileChannelReader.getCharArray(getRange().getStart(), getRange().getEnd(), isTrim()));
			if(value.isEmpty()) return null;
			Long rtnValue = Long.valueOf(value);
			if(rtnValue <= 0) return null;
			return rtnValue;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}