package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.io.FileChannelReader;

public class DoubleDivideByTwoComplexEvaluator extends DoubleDivideComplexTupleEvaluator {

	public DoubleDivideByTwoComplexEvaluator(String name, String... dependentColumnNames) {
		super(name, dependentColumnNames);
	}
	
	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		return super.parse(fileChannelReader,tupleRow)/2;
	}
	

}
