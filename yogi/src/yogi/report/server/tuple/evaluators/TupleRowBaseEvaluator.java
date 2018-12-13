package yogi.report.server.tuple.evaluators;

import yogi.base.evaluator.Evaluator;
import yogi.base.util.range.Range;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;




public abstract class TupleRowBaseEvaluator <V> implements Evaluator<TupleRow, V> {
	private static String[] EMPTY = new String[0];
	private String name;
	private Range<Integer> range;
	private boolean trim;
	
	public TupleRowBaseEvaluator(String name, Range<Integer> range, boolean trim) {
		super();
		this.name = name;
		this.range = range;
		this.trim = trim;
	}

	public String getName() {
		return name;
	}

	public Range<Integer>  getRange() {
		return range;
	}

	public boolean isTrim() {
		return trim;
	}
	
	public V evaluate(TupleRow object) {
		return object.getValue(this.getName(), this);
	}

	public abstract V parse(FileChannelReader fileChannelReader);
	
	public V parse(FileChannelReader fileChannelReader, TupleRow tupleRow){
		return parse(fileChannelReader);
	}

	public String[] getDependentColumnNames(){
		return EMPTY;
	}
	
}
