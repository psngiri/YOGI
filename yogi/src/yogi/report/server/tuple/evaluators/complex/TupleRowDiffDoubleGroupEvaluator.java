package yogi.report.server.tuple.evaluators.complex;

import java.util.ArrayList;
import java.util.List;

import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;

public  class TupleRowDiffDoubleGroupEvaluator extends TupleRowBaseGroupEvaluator<Double> implements GroupEvaluator<TupleRow, Double>{

	private TupleRowBaseEvaluator<Double> evaluator1;
	private TupleRowBaseEvaluator<Double> evaluator2;
	private String[] dependentColumnNames;

	public TupleRowDiffDoubleGroupEvaluator(String name, TupleRowBaseEvaluator<Double> evaluator1, TupleRowBaseEvaluator<Double> evaluator2) {
		super(name, null, false);
		this.evaluator1 = evaluator1;
		this.evaluator2 = evaluator2;
		List<String> dependentColumnsList = new ArrayList<String>();
		for(String column: evaluator1.getDependentColumnNames()){
			dependentColumnsList.add(column);
		}
		for(String column: evaluator2.getDependentColumnNames()){
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
	public Double evaluate(Group<TupleRow> group, int indexInGroup) {
		Double value1 = ((GroupEvaluator<TupleRow, Double>)evaluator1).evaluate(group, indexInGroup);
		Double value2 = ((GroupEvaluator<TupleRow, Double>)evaluator2).evaluate(group, indexInGroup);
		if(value1 == null){
			return -value2;
		}
		if(value2 == null){
			return value1;
		}
		return value1 - value2;
	}

}
