package yogi.period.date.report;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.report.condition.date.DateFormatter;

public class ReportDateFormatter extends DateFormatter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 272728831287990626L;
	
	@Override
	public String format(Date date) {
		if(date == DateManager.INFINITY||date == DateManager.EARLIEST_DATE || date == DateManager.UNKNOWN_DATE) return "";
		return super.format(date);
	}



}
