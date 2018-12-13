package yogi.period.date.io;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;

public class DDMMYYDateFormatter implements Formatter<Date> {
	public String format(Date date) {
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR)%100;
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%1$02d%2$02d%3$02d", day, month, year);
	}
}