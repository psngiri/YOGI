package yogi.report.server.tuple.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.io.FileChannelReader;



public class TupleRowIntegerEvaluator extends TupleRowBaseEvaluator<Integer> {

	public TupleRowIntegerEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public Integer parse(FileChannelReader fileChannelReader) {
		String value = new String(fileChannelReader.getCharArray(getRange().getStart(), getRange().getEnd(), isTrim()));
		if(value.isEmpty()) return null;
		if(value.equalsIgnoreCase("Inf")) return null;
		try {
			return Integer.valueOf(value);
		} catch (NumberFormatException e) {
			if(!value.equalsIgnoreCase("Inf")){
			e.printStackTrace();
			}
		}
		return null;
	}
}
