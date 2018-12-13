package yogi.period.date.io.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYDateFormatter;
import yogi.period.date.io.YYYYDashMMDashDDDateFormatter;

public class DDMMMYYDateFormatterTest extends TestCase
{
	public void testFormat()
	{
		DDMMMYYDateFormatter dDMMMYYDateFormatter = new DDMMMYYDateFormatter();
		Date date = DateManager.get().getDate(2007, Calendar.JUNE, 14);
		assertEquals("14JUN07", dDMMMYYDateFormatter.format(date));
	}

	public void testFormat1()
	{
		YYYYDashMMDashDDDateFormatter dDMMMYYDateFormatter = new YYYYDashMMDashDDDateFormatter();
		Date date1 = DateManager.get().getDate(2007, Calendar.JUNE, 14);
		assertEquals("2007-06-14", dDMMMYYDateFormatter.format(date1));
		Date date2 = DateManager.INFINITY;
		assertEquals("9999-12-31", dDMMMYYDateFormatter.format(date2));
	}
	
	public void testFormatter()
	{
		DDMMMYYDateFormatter dDMMMYYDateFormatter = new DDMMMYYDateFormatter();
		Date date = DateManager.EARLIEST_DATE;
		assertEquals("01JAN70", dDMMMYYDateFormatter.format(date));
		date = DateManager.UNKNOWN_DATE;
		assertEquals("31DEC69", dDMMMYYDateFormatter.format(date));
		date = DateManager.INFINITY;
		assertEquals("00XXX00", dDMMMYYDateFormatter.format(date));
	}
}