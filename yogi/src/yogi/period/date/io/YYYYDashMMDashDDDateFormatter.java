package yogi.period.date.io;

import java.io.Serializable;
import java.util.Calendar;

import yogi.base.io.Formatter;
import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;

public class YYYYDashMMDashDDDateFormatter implements Formatter<Date>, Serializable 
{
	private static final long serialVersionUID = 1L;

	public String format(Date date)
	{
		if (date == DateManager.INFINITY) return "9999-12-31";
		if (date == DateManager.UNKNOWN_DATE) return "";
		
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return String.format("%1$04d-%2$02d-%3$02d", year, month, day);
	}
}