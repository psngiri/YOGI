package yogi.period.time.range.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.period.time.io.HHMMTimeScanner;
import yogi.period.time.range.TimeRange;
import yogi.period.time.range.TimeRanges;
import yogi.period.time.range.io.TimeRangesScanner;

public class TimeRangesScannerTest extends TestCase {
	
	public void testCompare() {
		
		TimeRangesScanner scanner = new TimeRangesScanner(new HHMMTimeScanner());
		
		List<TimeRange> timeRanges1;
		List<TimeRange> timeRanges2;
		
		timeRanges1 = scanner.scan("0001-1100^1600-1800");
		timeRanges2 = scanner.scan("0001-1100^1600-1800");
		assertEquals(0, TimeRanges.compare(timeRanges1, timeRanges2));
		
		timeRanges1 = scanner.scan("");
		timeRanges2 = scanner.scan("0001-1100^1600-1800");
		assertEquals(-1, TimeRanges.compare(timeRanges1, timeRanges2));
		
		timeRanges1 = scanner.scan("0001-1100^1600-1800");
		timeRanges2 = scanner.scan("");
		assertEquals(1, TimeRanges.compare(timeRanges1, timeRanges2));

		timeRanges1 = scanner.scan("0001-1100^1600-1800^2000-2100");
		timeRanges2 = scanner.scan("0001-1100^1600-1800");
		assertEquals(1, TimeRanges.compare(timeRanges1, timeRanges2));

		timeRanges1 = scanner.scan("0001-1100^1600-1800");
		timeRanges2 = scanner.scan("0001-1100^1600-1800^2000-2100");
		assertEquals(-1, TimeRanges.compare(timeRanges1, timeRanges2));

		timeRanges1 = scanner.scan("0001-1100^1500-1800^2000-2100");
		timeRanges2 = scanner.scan("0001-1100^1600-1800^2000-2100");
		assertEquals(-1, TimeRanges.compare(timeRanges1, timeRanges2));
		
		timeRanges1 = scanner.scan("0001-1100^1600-1800^2000-2100");
		timeRanges2 = scanner.scan("0001-1100^1500-1800^2000-2100");
		assertEquals(1, TimeRanges.compare(timeRanges1, timeRanges2));
		
		timeRanges1 = scanner.scan("0001-1100^1600-1800^2000-2100");
		timeRanges2 = scanner.scan("0001-1100^1600-1800^2000-2100");
		assertEquals(0, TimeRanges.compare(timeRanges1, timeRanges2));
		
	}
}
