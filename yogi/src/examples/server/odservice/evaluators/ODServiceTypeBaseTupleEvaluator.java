package examples.server.odservice.evaluators;

import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public abstract class ODServiceTypeBaseTupleEvaluator<T> extends TupleRowComplexEvaluator<T> {

	public static final String TYPE = "serviceType";
	private Range<Integer> csRange;
	public ODServiceTypeBaseTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim) {
		super(name, range, trim, TYPE);
		this.csRange = csRange;

	}
	
	protected ODServiceTypeBaseTupleEvaluator(String name, Range<Integer> range, Range<Integer> csRange, boolean trim, String... dependentColumnNames) {
		super(name, range, trim, dependentColumnNames);
		this.csRange = csRange;

	}

	@Override
	public T parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		Range<Integer> range = getRange(tupleRow);
		return parse(fileChannelReader, range, this.isTrim());
	}

	protected Range<Integer> getRange(TupleRow tupleRow) {
		Range<Integer> range = getRange();
		String type = getServiceType(tupleRow);
		if(type.equals("*")){
			range = csRange;
		}
		return range;
	}

	protected String getServiceType(TupleRow tupleRow) {
		String type = tupleRow.getValue(TYPE);
		return type;
	}
	
	protected abstract T parse(FileChannelReader fileChannelReader, Range<Integer> range, boolean trim);
}
