package examples.server.nonstopop.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public abstract class NonStopOpTypeBaseTupleEvaluator<T> extends TupleRowComplexEvaluator<T> {

	private static final String TYPE = "legType";
	private Range<Integer> csRange;
	public NonStopOpTypeBaseTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, trim, TYPE);
		this.csRange = csRange;

	}

	@Override
	public T parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		Range<Integer> range = getRange();
		String type = tupleRow.getValue(TYPE);
		if(type.equals("*")){
			range = csRange;
		}
		if(range == null) return null;
		return parse(fileChannelReader, range, this.isTrim());
	}
	
	protected abstract T parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim);
}
