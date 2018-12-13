package yogi.report.server.tuple.evaluators;

import yogi.base.util.range.Range;
import yogi.report.group.GroupEvaluator;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;


public abstract class TupleRowBaseGroupEvaluator<V> extends TupleRowBaseEvaluator<V> implements GroupEvaluator<TupleRow, V> {


	public TupleRowBaseGroupEvaluator(String name, Range<Integer> range, boolean trim) {
		super(name, range, trim);
	}

	@Override
	public V parse(FileChannelReader fileChannelReader) {
		return null;
	}
	

}
