package yogi.report.condition.timeDb;

import yogi.report.condition.timestamp.TimestampFormatter;

public class TimeDbFormatter extends TimestampFormatter{

	private static final long serialVersionUID = 3822032051835549341L;

	@Override
	public String format(Long timeInMillis) {
		String timestamp = super.format(timeInMillis);
		return timestamp.substring(timestamp.indexOf(" ")+1);
	}

}
