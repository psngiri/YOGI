package yogi.report.server.tuple.evaluators;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;

public class TupleRowWrapperEvaluator<T> extends TupleRowComplexEvaluator<T> {
	
	private String columnToWrap;

	public TupleRowWrapperEvaluator(String name, String columnToWrap) {
		super(name, null, false,columnToWrap);
		this.columnToWrap = columnToWrap;
	}

	@Override
	public T parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		return tupleRow.getValue(columnToWrap);
	}
}

