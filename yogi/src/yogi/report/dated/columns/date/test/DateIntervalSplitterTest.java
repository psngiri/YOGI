package yogi.report.dated.columns.date.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.period.interval.Interval;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.date.DateIntervalSplitter;

public class DateIntervalSplitterTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testSplit()
	{
		DateIntervalSplitter splitter = new DateIntervalSplitter();
		Interval scan = intervalScanner.scan("01JAN2007 01JAN2007 1.1111..");
		List<Interval> split = splitter.split(scan);
		assertEquals(1, split.size());
		assertEquals("01JAN2007 01JAN2007 M......", split.get(0).toString());
		split = splitter.split(intervalScanner.scan("01JAN2007 07JAN2007 1.111.."));
		assertEquals(4, split.size());
		assertEquals("01JAN2007 01JAN2007 M......", split.get(0).toString());
		assertEquals("03JAN2007 03JAN2007 ..W....", split.get(1).toString());
		assertEquals("04JAN2007 04JAN2007 ...R...", split.get(2).toString());
		assertEquals("05JAN2007 05JAN2007 ....F..", split.get(3).toString());
	}
	
}
