package yogi.report.dated.columns.year.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.period.interval.Interval;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.year.YearIntervalSplitter;

public class YearIntervalSplitterTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testSplit()
	{
		YearIntervalSplitter splitter = new YearIntervalSplitter();
		List<Interval> split = splitter.split(intervalScanner.scan("01JAN2007 31JAN2007 1.111.."));
		assertEquals(1, split.size());
		assertEquals("01JAN2007 31JAN2007 M.WRF..", split.get(0).toString());
		split = splitter.split(intervalScanner.scan("01JAN2007 28MAR2008 1.111.."));
		assertEquals(2, split.size());
		assertEquals("01JAN2007 31DEC2007 M.WRF..", split.get(0).toString());
		assertEquals("01JAN2008 28MAR2008 M.WRF..", split.get(1).toString());
	}
	
}
