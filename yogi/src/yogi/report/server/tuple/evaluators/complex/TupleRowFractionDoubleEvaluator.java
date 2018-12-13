package yogi.report.server.tuple.evaluators.complex;

import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;
import yogi.base.util.FractionDouble;

public  class TupleRowFractionDoubleEvaluator extends TupleRowBaseEvaluator<FractionDouble> {

	private String[] dependentColumnNames;

	public TupleRowFractionDoubleEvaluator(String name, String numeratorColumnName, String denominatorColumnName) {
		super(name, null, false);
		this.dependentColumnNames = new String[] {numeratorColumnName, denominatorColumnName};
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}
	
	public FractionDouble parse(FileChannelReader fileChannelReader){
		return null;
	}
	
	public FractionDouble parse(FileChannelReader fileChannelReader, TupleRow tupleRow){
		FractionDouble rtnValue = FractionDouble.BLANK;
		Double numerator = tupleRow.getValue(dependentColumnNames[0]);
		Double denominator = tupleRow.getValue(dependentColumnNames[1]);
		if(numerator != null && denominator != null){
			rtnValue = new FractionDouble(numerator, denominator);
		}
		return rtnValue;
	}

}
