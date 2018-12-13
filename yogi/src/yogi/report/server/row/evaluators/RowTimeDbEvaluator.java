package yogi.report.server.row.evaluators;

import java.sql.Time;

import yogi.report.server.row.Row;

public class RowTimeDbEvaluator extends RowBaseEvaluator<Long> {

	public RowTimeDbEvaluator(String columnName) {
		super(columnName);
	}

	public Long evaluate(Row object) {
		Long value=null;
			Time time= null;
				time = object.getValue(this.getColumnName());
				if(time != null){
					value = time.getTime();
					value = value - time.getTimezoneOffset()*60*1000;
				}
		return value;
	}
}
