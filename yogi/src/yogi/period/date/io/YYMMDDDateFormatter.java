package yogi.period.date.io;

import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;

public class YYMMDDDateFormatter implements Formatter<Date> {
	public static String InfinityDate="999999";
	public static String EarliestDate="000000";
	
	public String format(Date date) {
		if(date == DateManager.INFINITY) return InfinityDate;
		if(date == DateManager.EARLIEST_DATE) return EarliestDate;
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR)%100;
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return String.format("%1$02d%2$02d%3$02d", year, month, day);
	}
}
