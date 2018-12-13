package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class DoubleDivideComplexTupleEvaluator extends TupleRowComplexEvaluator<Double> {

	public DoubleDivideComplexTupleEvaluator(String name, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		Double value0 = tupleRow.getValue(dependentColumnNames[0]);
		Double value1 = tupleRow.getValue(dependentColumnNames[1]);
		return value0/value1;
	}
	
}
