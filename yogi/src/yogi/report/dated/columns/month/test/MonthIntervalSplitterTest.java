package yogi.report.dated.columns.month.test;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.month.MonthIntervalSplitter;

import junit.framework.TestCase;

public class MonthIntervalSplitterTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testSplit()
	{
		MonthIntervalSplitter splitter = new MonthIntervalSplitter();
		List<Interval> split = splitter.split(intervalScanner.scan("01JAN2007 31JAN2007 1.111.."));
		assertEquals(1, split.size());
		assertEquals("01JAN2007 31JAN2007 M.WRF..", split.get(0).toString());
		split = splitter.split(intervalScanner.scan("01JAN2007 28MAR2008 1.111.."));
		assertEquals(15, split.size());
		assertEquals("01JAN2007 31JAN2007 M.WRF..", split.get(0).toString());
		assertEquals("01FEB2007 28FEB2007 M.WRF..", split.get(1).toString());
		assertEquals("01MAR2007 31MAR2007 M.WRF..", split.get(2).toString());
		assertEquals("01APR2007 30APR2007 M.WRF..", split.get(3).toString());
		assertEquals("01MAY2007 31MAY2007 M.WRF..", split.get(4).toString());
		assertEquals("01JUN2007 30JUN2007 M.WRF..", split.get(5).toString());
		assertEquals("01JUL2007 31JUL2007 M.WRF..", split.get(6).toString());
		assertEquals("01AUG2007 31AUG2007 M.WRF..", split.get(7).toString());
		assertEquals("01SEP2007 30SEP2007 M.WRF..", split.get(8).toString());
		assertEquals("01OCT2007 31OCT2007 M.WRF..", split.get(9).toString());
		assertEquals("01NOV2007 30NOV2007 M.WRF..", split.get(10).toString());
		assertEquals("01DEC2007 31DEC2007 M.WRF..", split.get(11).toString());
		assertEquals("01JAN2008 31JAN2008 M.WRF..", split.get(12).toString());
		assertEquals("01FEB2008 29FEB2008 M.WRF..", split.get(13).toString());
		assertEquals("01MAR2008 28MAR2008 M.WRF..", split.get(14).toString());
	}
	
}
