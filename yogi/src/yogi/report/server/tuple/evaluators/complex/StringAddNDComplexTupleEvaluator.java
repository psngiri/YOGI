package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddNDComplexTupleEvaluator extends TupleRowComplexEvaluator<String> {

	public StringAddNDComplexTupleEvaluator(String name, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		String value0 = tupleRow.getValue(dependentColumnNames[0]);
		String value1 = tupleRow.getValue(dependentColumnNames[1]);
		try {
			if(value0.compareTo(value1) <= 0){
				return value0+value1;
			}else{
				return value1+value0;
			}
		}
		catch(Exception e)
		{
			return null;
		}		
	}
}