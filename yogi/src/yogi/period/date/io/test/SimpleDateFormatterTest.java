package yogi.period.date.io.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYYYDateFormatter;
import yogi.period.date.io.SimpleDateFormatter;

import junit.framework.TestCase;

public class SimpleDateFormatterTest extends TestCase {

	/*
	 * Test method for 'yogi.period.date.io.SimpleDateFormatter.format(Date)'
	 */
	public void testFormat() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyy");
		SimpleDateFormatter simpleDateFormatter = new SimpleDateFormatter(simpleDateFormat);
		Date date = DateManager.get().getDate(2007, Calendar.JUNE, 14);
		assertEquals("14Jun07", simpleDateFormatter.format(date));
		date = DateManager.get().getDate(2069, Calendar.JUNE, 14);
		DDMMMYYYYDateFormatter formatter = new DDMMMYYYYDateFormatter();
		assertEquals("14JUN2069", formatter.format(date));
		date = DateManager.get().getDate(2069, Calendar.DECEMBER, 30);
		assertEquals("30DEC2069", formatter.format(date));
	}

}
