package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowComplexEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class StringAddComplexTupleEvaluator extends TupleRowComplexEvaluator<String> {

	public StringAddComplexTupleEvaluator(String name, String... dependentColumnNames) {
		super(name, null, false, dependentColumnNames);
	}

	@Override
	public String parse(FileChannelReader fileChannelReader, TupleRow tupleRow) {
		String[] dependentColumnNames = this.getDependentColumnNames();
		String rtnValue = "";
		for(String name: dependentColumnNames){
			Object value = tupleRow.getValue(name);
			if(value != null) rtnValue += value;
		}
		return rtnValue;
	}
	
}
