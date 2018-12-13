package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class DoubleDivideLongComplexTupleEvaluator extends TupleRowComplexEvaluator<Double> {

	public DoubleDivideLongComplexTupleEvaluator(String name, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		long value0 = tupleRow.getValue(dependentColumnNames[0]);
		long value1 = tupleRow.getValue(dependentColumnNames[1]);
		Double rtnValue = ((double) value0/value1);
		return rtnValue;
	}
	
}
