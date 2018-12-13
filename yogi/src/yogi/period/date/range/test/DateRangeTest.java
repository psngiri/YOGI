package yogi.period.date.range.test;

import junit.framework.TestCase;

import yogi.base.io.Scanner;
import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.date.io.YYYYMMDDDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.io.DateRangeScanner;

public class DateRangeTest extends TestCase {
	private final Scanner<Date, String> dateScannerYYYYMMDD = new YYYYMMDDDateScanner();

		public void testValidDates()
		{
			try {
				new DateRangeScanner().scan("01SEP2007 31AUG2007");
				fail("expected to fail");
			} catch (RuntimeException e) {}
			
			DateRange dateRange = new DateRangeScanner().scan("01SEP2007 01SEP2007");
			assertNotNull(dateRange);
		}
	
		public void testRangeIntersectionWhereIntersectingRangeComesBeforeThisRange()
		{
			Date startDate = dateScannerYYYYMMDD.scan("20100801").create();
			Date endDate = dateScannerYYYYMMDD.scan("20100831").create();
			DateRange mainRange = new DateRange(startDate, endDate);
			
			startDate = dateScannerYYYYMMDD.scan("20100601").create();
			endDate = dateScannerYYYYMMDD.scan("20100630").create();
			DateRange secondaryRange = new DateRange(startDate, endDate);
			assertTrue(!mainRange.intersects(secondaryRange));
			assertTrue(!secondaryRange.intersects(mainRange));
		}
		
		public void testRange()
		{
			// 
			Date startDate = dateScannerYYYYMMDD.scan("20110511").create();
			Date endDate = dateScannerYYYYMMDD.scan("20110912").create();
			DateRange mainRange = new DateRange(startDate, endDate);
			
			// 999999 
			startDate = dateScannerYYYYMMDD.scan("20111028").create();
			//endDate = dateScannerYYYYMMDD.scan("20100630").create();
			endDate=DateManager.INFINITY;
			DateRange secondaryRange = new DateRange(startDate, endDate);
			assertTrue(!mainRange.intersects(secondaryRange));
			assertTrue(!secondaryRange.intersects(mainRange));
		}
		
		
}
