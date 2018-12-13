package yogi.report.server.row.evaluators;

import yogi.base.evaluator.Evaluator;
import yogi.report.server.row.Row;



public abstract class RowBaseEvaluator <V> implements Evaluator<Row, V> {
	private String columnName;
	
	public RowBaseEvaluator(String columnName) {
		super();
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getComplexName(){
		return null;
	}
	
	public abstract V evaluate(Row object);
}
