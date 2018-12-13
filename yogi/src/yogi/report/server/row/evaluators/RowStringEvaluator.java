package yogi.report.server.row.evaluators;

import yogi.report.server.row.Row;



public class RowStringEvaluator extends RowBaseEvaluator<String> {

	public RowStringEvaluator(String columnName) {
		super(columnName);
	}

	public String evaluate(Row object) {
		String value="";
		try {
			value = object.getValue(this.getColumnName()).toString();
		} catch (Exception e) {
			value = value.toString();
		}
		return value;
	}
}
