package yogi.report.server.row.evaluators;

import yogi.report.server.row.Row;



public class RowIntegerEvaluator extends RowBaseEvaluator<Integer> {

	public RowIntegerEvaluator(String columnName) {
		super(columnName);
	}

	public Integer evaluate(Row object) {
		Integer value;
		try {
			value = object.getValue(this.getColumnName());
		} catch (Exception e) {
			throw new RuntimeException("Error parsing column:"+this.getColumnName(), e);
		}
		return value;
	}
}
