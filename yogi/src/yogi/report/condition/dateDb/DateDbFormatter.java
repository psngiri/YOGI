package yogi.report.condition.dateDb;

import yogi.report.condition.timestamp.TimestampFormatter;

public class DateDbFormatter extends TimestampFormatter {

	private static final long serialVersionUID = 1L;

	public String format(Long timeInMillis) {
		String date = super.format(timeInMillis);
		return date.substring(0,date.indexOf(" "));
	}

}
