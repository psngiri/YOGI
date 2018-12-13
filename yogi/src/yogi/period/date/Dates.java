package yogi.period.date;

import java.util.Calendar;

public class Dates {

	public static Date addDays(Date date, int days)
	{
		if(date.equals(DateManager.INFINITY)) return date;
		return DateManager.get().getDate(date.getValue() + days);
	}
	
	public static Date subtractDays(Date date, int days)
	{
		if(date.equals(DateManager.INFINITY)) return date;
		return DateManager.get().getDate(date.getValue() - days);
	}
	
	public static boolean isContigous(Date date1, Date date2)
	{
		return (getDistance(date1, date2) == 1);
	}
	
	public static long getDistance(Date date1, Date date2)
	{
		return Math.abs(date1.getValue() - date2.getValue());
	}
	
	public static Date addYears(Date date, int count) {
		Calendar calendar = DateAssistant.get().getCalendar(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		year = year + count;

		YMDDateCreator creator = new YMDDateCreator();
		creator.setYear(year);
		creator.setMonth(month);
		creator.setDay(day);
		return creator.create();
	}
}
