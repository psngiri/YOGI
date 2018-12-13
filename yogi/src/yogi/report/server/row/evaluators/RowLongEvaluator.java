package yogi.report.server.row.evaluators;

import yogi.report.server.row.Row;



public class RowLongEvaluator extends RowBaseEvaluator<Long> {

	public RowLongEvaluator(String columnName) {
		super(columnName);
	}

	public Long evaluate(Row object) {
		Long value = null;
		Object baseValue = null;
		try {
			baseValue = object.getValue(this.getColumnName());
			value = (Long) baseValue;
		} catch (Exception e) {
			if(baseValue instanceof Number){
				String string = baseValue.toString();
				int indexOf = string.indexOf('.');
				if(indexOf > 0){
					string = string.substring(0, indexOf);
				}
				value = Long.valueOf(string);
			}
			return value;
		}
		return value;
	}
}
