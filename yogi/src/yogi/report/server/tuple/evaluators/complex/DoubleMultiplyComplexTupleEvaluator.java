package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class DoubleMultiplyComplexTupleEvaluator extends TupleRowComplexEvaluator<Double>{

	public DoubleMultiplyComplexTupleEvaluator(String name, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		if(dependentColumnNames.length == 0) return 0d;
		Double rtnValue = 1d;
		for(String columnName: dependentColumnNames){
			Double value = tupleRow.getValue(columnName);
			if(value == null) return 0d;
			rtnValue = rtnValue * value;
		}
		return rtnValue;
	}

}
