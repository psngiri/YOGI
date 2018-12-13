package yogi.report.server.tuple.evaluators.complex;

import java.util.ArrayList;
import java.util.List;

import yogi.report.group.Group;
import yogi.report.group.GroupEvaluator;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;
import yogi.report.server.tuple.io.FileChannelReader;



public class DoubleMultiplyComplexTupleGroupEvaluator extends TupleRowBaseGroupEvaluator<Double> implements GroupEvaluator<TupleRow, Double> {
	private String[] dependentColumnNames;

	private TupleRowBaseGroupEvaluator<Double>[] evaluators;
	@SafeVarargs
	public DoubleMultiplyComplexTupleGroupEvaluator(String name, TupleRowBaseGroupEvaluator<Double>... evaluators) {
		super(name, null, false);
		this.evaluators = evaluators;
		List<String> dependentColumnsList = new ArrayList<String>();
		for(TupleRowBaseEvaluator<Double> evaluator: evaluators){
			for(String column: evaluator.getDependentColumnNames()){
				dependentColumnsList.add(column);
			}
		}
		dependentColumnNames = dependentColumnsList.toArray(new String[0]);
	}

	@Override
	public Double evaluate(Group<TupleRow> group, int indexInGroup) {
		if(evaluators.length == 0) return 0d;

		Double rtnValue = 1d;
		for(TupleRowBaseEvaluator<Double> evaluator: evaluators){
			@SuppressWarnings("unchecked")
			Double value = ((GroupEvaluator<TupleRow, Double>)evaluator).evaluate(group, indexInGroup);
			if(value == null) return 0d;
			rtnValue = rtnValue * value;
		}
		return rtnValue;
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}

	@Override
	public Double parse(FileChannelReader fileChannelReader) {
		return null;
	}
	
}
