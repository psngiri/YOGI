package yogi.period.date.range.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.period.date.io.DDMMMYYDateScanner;
import yogi.period.date.io.YYMMMDDPartialDateScanner;
import yogi.period.date.range.DateRange;
import yogi.period.date.range.DateRanges;
import yogi.period.date.range.io.PartialDateRangesScanner;

public class PartialDateRangesScannerTest extends TestCase {

		public void testValidDateRanges()
		{

			YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
			PartialDateRangesScanner dateRangesScanner = new PartialDateRangesScanner(dateScanner);
			
			String dateString = "";
			List<DateRange> ranges = new ArrayList<DateRange>();

			dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("12APR13").create());
			
			dateString = "22FEB-10MAR";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("22FEB2014", ranges.get(0).getStart().toString());
			assertEquals("10MAR2014", ranges.get(0).getEnd().toString());

			dateString = "22FEB-10APR";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("22FEB2014", ranges.get(0).getStart().toString());
			assertEquals("10APR2014", ranges.get(0).getEnd().toString());

			dateString = "01MAY-01JUN";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("01MAY2013", ranges.get(0).getStart().toString());
			assertEquals("01JUN2013", ranges.get(0).getEnd().toString());
			
			dateString = "22FEB-10MAR^01JUN-01AUG";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("01JUN2013", ranges.get(0).getStart().toString());
			assertEquals("01AUG2013", ranges.get(0).getEnd().toString());
			assertEquals("22FEB2014", ranges.get(1).getStart().toString());
			assertEquals("10MAR2014", ranges.get(1).getEnd().toString());

			dateString = "22FEB-20APR^01JUN-01AUG";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("01JUN2013", ranges.get(0).getStart().toString());
			assertEquals("01AUG2013", ranges.get(0).getEnd().toString());
			assertEquals("22FEB2014", ranges.get(1).getStart().toString());
			assertEquals("20APR2014", ranges.get(1).getEnd().toString());
			
			dateString = "22FEB-20MAR^01APR-01AUG";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("22FEB2014", ranges.get(0).getStart().toString());
			assertEquals("20MAR2014", ranges.get(0).getEnd().toString());
			assertEquals("01APR2014", ranges.get(1).getStart().toString());
			assertEquals("01AUG2014", ranges.get(1).getEnd().toString());
			
			dateString = "22FEB13-20MAR13^01APR13-01AUG13";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("22FEB2013", ranges.get(0).getStart().toString());
			assertEquals("20MAR2013", ranges.get(0).getEnd().toString());
			assertEquals("01APR2013", ranges.get(1).getStart().toString());
			assertEquals("01AUG2013", ranges.get(1).getEnd().toString());
			
			dateString = "22FEB13-20MAR13^01APR14-01AUG14";
			ranges = dateRangesScanner.scan(dateString);
			System.out.println("Date Ranges \t: " + ranges);
			assertEquals("22FEB2013", ranges.get(0).getStart().toString());
			assertEquals("20MAR2013", ranges.get(0).getEnd().toString());
			assertEquals("01APR2014", ranges.get(1).getStart().toString());
			assertEquals("01AUG2014", ranges.get(1).getEnd().toString());

		}
		
		public void testSubtractDataRanges() {
			YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
			PartialDateRangesScanner dateRangesScanner = new PartialDateRangesScanner(dateScanner);
			
			dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("15AUG13").create());
			
			String dateString = "";			
			List<DateRange> ranges1 = new ArrayList<DateRange>();
			List<DateRange> ranges2 = new ArrayList<DateRange>();
			
			dateString = "15AUG13-19NOV13^28NOV13-12DEC13^29DEC13-12MAR14^22APR14-06JUN14";			
			ranges1 = dateRangesScanner.scan(dateString);
			
			dateString = "15AUG13-19NOV13^28NOV13-12DEC13^29DEC13-12MAR14^22APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			
			assertTrue(DateRanges.subtract(ranges1, ranges2).isEmpty() && DateRanges.subtract(ranges2, ranges1).isEmpty());				
			
		}
		
		public void testCompareDataRanges() {
			YYMMMDDPartialDateScanner dateScanner = new YYMMMDDPartialDateScanner();
			PartialDateRangesScanner dateRangesScanner = new PartialDateRangesScanner(dateScanner);
			
			dateScanner.setReferenceDate(new DDMMMYYDateScanner().scan("15AUG13").create());
			
			String dateString = "";			
			List<DateRange> ranges1 = new ArrayList<DateRange>();
			List<DateRange> ranges2 = new ArrayList<DateRange>();
			
			dateString = "";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(0, DateRanges.compare(ranges1, ranges2));
			
			dateString = "";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(-1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-12DEC13^29DEC13-18MAR14^02APR14-06JUN14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(-1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-12DEC13^29DEC13-18MAR14^02APR14-06JUN14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(-1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-15DEC13^29DEC13-18MAR14^02APR14-06JUN14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(1, DateRanges.compare(ranges1, ranges2));
			
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges1 = dateRangesScanner.scan(dateString);
			dateString = "12AUG13-19NOV13^28NOV13-14DEC13^31DEC13-18MAR14^02APR14-06JUN14";
			ranges2 = dateRangesScanner.scan(dateString);
			assertEquals(0, DateRanges.compare(ranges1, ranges2));
		}
}
