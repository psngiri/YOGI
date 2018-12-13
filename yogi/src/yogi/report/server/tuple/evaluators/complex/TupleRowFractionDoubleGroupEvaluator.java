package yogi.report.server.tuple.evaluators.complex;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.FractionDouble;
import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;

public  class TupleRowFractionDoubleGroupEvaluator extends TupleRowBaseGroupEvaluator<FractionDouble> implements GroupEvaluator<TupleRow, FractionDouble>{

	private TupleRowBaseEvaluator<Double> numeratorEvaluator;
	private TupleRowBaseEvaluator<Double> denominatorEvaluator;
	private String[] dependentColumnNames;

	public TupleRowFractionDoubleGroupEvaluator(String name, TupleRowBaseEvaluator<Double> numeratorEvaluator, TupleRowBaseEvaluator<Double> denominatorEvaluator) {
		super(name, null, false);
		this.numeratorEvaluator = numeratorEvaluator;
		this.denominatorEvaluator = denominatorEvaluator;
		List<String> dependentColumnsList = new ArrayList<String>();
		for(String column: numeratorEvaluator.getDependentColumnNames()){
			dependentColumnsList.add(column);
		}
		for(String column: denominatorEvaluator.getDependentColumnNames()){
			dependentColumnsList.add(column);
		}
		dependentColumnNames = dependentColumnsList.toArray(new String[0]);
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FractionDouble evaluate(Group<TupleRow> group, int indexInGroup) {
		FractionDouble rtnValue = FractionDouble.BLANK;
		Double numerator = ((GroupEvaluator<TupleRow, Double>)numeratorEvaluator).evaluate(group, indexInGroup);
		Double denominator = ((GroupEvaluator<TupleRow, Double>)denominatorEvaluator).evaluate(group, indexInGroup);
		if(numerator != null && denominator != null){
			rtnValue = new FractionDouble(numerator, denominator);
		}
		return rtnValue;
	}

}
