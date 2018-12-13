package yogi.report.server.tuple.evaluators;

import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowDummyEvaluator<T> extends TupleRowBaseEvaluator<T>{

	private T dummyValue;
	
	public TupleRowDummyEvaluator(String name) {
		super(name, null, false);
	}
	
	public TupleRowDummyEvaluator(String name,T dummyValue) {
		super(name, null, false);
		this.dummyValue = dummyValue;
	}

	@Override
	public T parse(FileChannelReader fileChannelReader) {
		return dummyValue;
	}	
}
