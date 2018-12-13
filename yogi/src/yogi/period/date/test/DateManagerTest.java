package yogi.period.date.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.DateManager;
import yogi.period.date.io.YYYYDashMMDashDDDateFormatter;
import yogi.report.condition.date.DateFormatter;

public class DateManagerTest extends TestCase
{
	public void testGetDate()
	{
		Date date = DateManager.get().getDate(2007, 4, 31);
		Calendar calendar = DateAssistant.get().getCalendar(date);
		assertEquals(2007, calendar.get(Calendar.YEAR));
		assertEquals(31, calendar.get(Calendar.DAY_OF_MONTH));
		assertEquals(4, calendar.get(Calendar.MONTH));
	}

	public void testDate()
	{
		DateFormatter formatter = new DateFormatter();
		Date d1 = DateManager.get().getDate(Long.MAX_VALUE);
		Date d2 = DateManager.get().getDate(0);
		Date d3 = DateManager.get().getDate(-1);
		System.out.println("Date1a : " + formatter.format(d1));
		System.out.println("Date1b : " + formatter.format(d2));
		System.out.println("Date1c : " + formatter.format(d3));
		if (d1 == d3)
		{
			System.out.println("equal");
		}
		
		System.out.println("Date2a : " + getYYYYMMDDDate(Long.MAX_VALUE));
		System.out.println("Date2b : " + getYYYYMMDDDate(0));
		System.out.println("Date2c : " + getYYYYMMDDDate(-1));
	}

	private String getYYYYMMDDDate(long daysSinceEpoc)
	{
		Date date = DateManager.get().getDate(daysSinceEpoc);
		YYYYDashMMDashDDDateFormatter dateFormatter = new YYYYDashMMDashDDDateFormatter();
		return dateFormatter.format(date);
	}
}