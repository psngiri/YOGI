package yogi.report.server.tuple.evaluators.complex;

import yogi.report.group.Group;
import yogi.report.server.tuple.TupleRow;
import yogi.report.server.tuple.evaluators.TupleRowBaseGroupEvaluator;

public class DependentGroupEvaluator<T> extends TupleRowBaseGroupEvaluator<T>{
	private String[] dependentColumnNames;
	public DependentGroupEvaluator(String name) {
		super(name, null, false);
		dependentColumnNames = new String[]{getName()};
	}

	@Override
	public T evaluate(Group<TupleRow> group, int indexInGroup) {
		TupleRow tupleRow = group.getItem(indexInGroup);
		return tupleRow.getValue(getName());
	}

	@Override
	public String[] getDependentColumnNames() {
		return dependentColumnNames;
	}
}

