package yogi.report.server.row.evaluators;

import java.sql.Timestamp;

import yogi.report.server.row.Row;

public class RowTimestampEvaluator extends RowBaseEvaluator<Long> {

	public RowTimestampEvaluator(String columnName) {
		super(columnName);
	}

	public Long evaluate(Row object) {
		Long value=null; 
		Timestamp timestamp= null;
		timestamp = object.getValue(this.getColumnName());
		if(timestamp != null){
			value = timestamp.getTime();
			value = value - timestamp.getTimezoneOffset()*60*1000;
		}	
		return value;
	}
}
