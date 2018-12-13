package yogi.report.server.row.evaluators;

import java.sql.Date;

import yogi.report.server.row.Row;

public class RowDateDbEvaluator extends RowBaseEvaluator<Long> {

	public RowDateDbEvaluator(String columnName) {
		super(columnName);
	}

	public Long evaluate(Row object) {
		Long value = null;
		Date date= null;
		date = object.getValue(this.getColumnName());
		if(date != null){
			value = date.getTime();
		//	value = value - date.getTimezoneOffset()*60*1000;
		}
		return value;
	}
}
