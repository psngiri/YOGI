package yogi.report.condition.date.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.io.YYMMMDDPartialDateScanner;
import yogi.period.date.range.io.PartialDateRangesScanner;
import yogi.report.condition.DateRangesListInCondition;

public class DateRangesListInConditionTest extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}
	
	public void testDateMatchesCondition() {
		YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
		PartialDateRangesScanner dateRangesScanner = new PartialDateRangesScanner(dateScanner);
		
		assertFalse(new DateRangesListInCondition("").satisfied(null));
		assertTrue(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14").satisfied(dateRangesScanner.scan("01JAN14-10JAN14^20JAN14-30JAN14")));
		assertTrue(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-10JAN14^20JAN14-30JAN14")));
		assertTrue(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("02JAN14-10JAN14^20JAN14-30JAN14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-11JAN14^20JAN14-30JAN14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-10JAN14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-30JAN14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-25FEB14")));
		assertFalse(new DateRangesListInCondition("01JAN14-10JAN14^20JAN14-30JAN14,01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14").satisfied(dateRangesScanner.scan("01JAN14-10JAN14^20JAN14-30JAN14^01FEB14-05FEB14^11FEB14-15FEB14^21FEB14-25FEB14")));
		
		
	}

}
