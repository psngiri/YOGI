package yogi.report.server.tuple;

import yogi.report.server.tuple.evaluators.TupleRowBaseEvaluator;


public interface TupleRow {
	<V> V getValue(String name);
	<V> V getValue(String name, TupleRowBaseEvaluator<V> evaluator);
}
