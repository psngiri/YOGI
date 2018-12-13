package yogi.period.date.io.test;

import java.util.Calendar;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DDMMMYYDateScanner;

import junit.framework.TestCase;

public class DDMMMYYDateScannerTest extends TestCase {

	/*
	 * Test method for 'yogi.period.date.io.DDMMMYYDateScanner.scan(String)'
	 */
	public void testScan() {
		DDMMMYYDateScanner scanner = new DDMMMYYDateScanner();
		String dateString = "14JUN07";
		Date date = scanner.scan(dateString).create();
		assertEquals(DateManager.get().getDate(07, Calendar.JUNE, 14), date);
	}
	public void testScanLowerCaseMonth() {
		DDMMMYYDateScanner scanner = new DDMMMYYDateScanner();
		String dateString = "14Jun07";
		Date date = scanner.scan(dateString).create();
		assertEquals(DateManager.get().getDate(07, Calendar.JUNE, 14), date);
	}

}
