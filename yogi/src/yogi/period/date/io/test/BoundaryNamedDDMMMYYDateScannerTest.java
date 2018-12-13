package yogi.period.date.io.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.BoundaryNamedDDMMMYYDateScanner;

public class BoundaryNamedDDMMMYYDateScannerTest extends TestCase {

	public void testScan() {
		BoundaryNamedDDMMMYYDateScanner scanner = new BoundaryNamedDDMMMYYDateScanner();
		String dateStr;
		Date date;
		
		dateStr = "01JAN13";
		date = scanner.scan(dateStr).create();
		assertEquals(DateManager.get().getDate(13, Calendar.JANUARY, 01), date);
	
		dateStr = "01JAN14";
		date = scanner.scan(dateStr).create();
		assertEquals(DateManager.get().getDate(14, Calendar.JANUARY, 01), date);
	
		dateStr = "01JAN15";
		date = scanner.scan(dateStr).create();
		assertEquals(DateManager.get().getDate(15, Calendar.JANUARY, 01), date);
	

	}

}
