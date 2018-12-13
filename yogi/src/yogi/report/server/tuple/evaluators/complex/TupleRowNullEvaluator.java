package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowNullEvaluator<T> extends TupleRowBaseEvaluator<T>{

	public TupleRowNullEvaluator(String name) {
		super(name, null, false);
	}

	public T parse(FileChannelReader fileChannelReader) {
		return null;
	}
	
}