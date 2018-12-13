package yogi.report.server.row.evaluators;

import java.sql.Timestamp;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.report.server.row.Row;



public class RowDateEvaluator extends RowBaseEvaluator<Date> {

	public RowDateEvaluator(String columnName) {
		super(columnName);
	}

	public Date evaluate(Row object) {
		Date value=null;
		Timestamp timeStamp = null;
		try {
			timeStamp = object.getValue(this.getColumnName());
			if(timeStamp != null){
				value = DateManager.get().getDate(timeStamp.getTime()/DateManager.MillisInADay);
			}
		} catch (Exception e) {
			value = DateManager.UNKNOWN_DATE;
		}
		return value;
	}
}
