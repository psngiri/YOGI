package yogi.report.server.row.evaluators;

import yogi.report.server.row.Row;



public class RowDoubleEvaluator extends RowBaseEvaluator<Double> {

	public RowDoubleEvaluator(String columnName) {
		super(columnName);
	}

	public Double evaluate(Row object) {
		Double value = null;
		Object baseValue = null;
		try {
			baseValue = object.getValue(this.getColumnName());
			value = (Double) baseValue;
		} catch (Exception e) {
			if(baseValue instanceof Number){
				String string = baseValue.toString();
				value = Double.valueOf(string);
			}
			return value;
		}
		return value;
	}
}
