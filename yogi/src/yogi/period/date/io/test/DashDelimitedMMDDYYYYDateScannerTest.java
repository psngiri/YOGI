package yogi.period.date.io.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.DashDelimitedMMDDYYYYDateScanner;

public class DashDelimitedMMDDYYYYDateScannerTest extends TestCase {

	public void testScan() {
		DashDelimitedMMDDYYYYDateScanner scanner = new DashDelimitedMMDDYYYYDateScanner();
		String dateString = "06/14/2007";
		Date date = scanner.scan(dateString).create();
		assertEquals(DateManager.get().getDate(07, Calendar.JUNE, 14), date);
	}

}
