package examples.server.nonstopop.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;

public class NonStopOpTypeLongTupleEvaluator extends NonStopOpTypeBaseTupleEvaluator<Long> {

	public NonStopOpTypeLongTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, csRange, trim);
	}

	public Long parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim) {
		String value = new String(fileChannelReader.getCharArray(range.getStart(), range.getEnd(), isTrim()));
		if(value.isEmpty())return null;
		if(value.equalsIgnoreCase("Inf")) return null;
		try {
			return Long.valueOf(value);
		} catch (NumberFormatException e) {
			if(!value.equalsIgnoreCase("Inf")){
			e.printStackTrace();
			}
		}
		return null;
	}
}
