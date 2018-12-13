package yogi.period.date.io;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;

public class SimpleDateFormatter implements Formatter<Date> {
	DateFormat dateFormat;
	
	public SimpleDateFormatter(DateFormat dateFormat) {
		super();
		this.dateFormat = dateFormat;
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	}

	public String format(Date date) {
		Calendar calendar = DateAssistant.get().getCalendar(date);
		return dateFormat.format(calendar.getTime());
	}

}
