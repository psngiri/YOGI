package yogi.report.dated.columns.month.test;

import junit.framework.TestCase;

import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.month.MonthColumn;

public class MonthColumnTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testGetValue()
	{
		MonthColumn monthColumn = new MonthColumn();
		assertEquals(0, (int)monthColumn.getValue(intervalScanner.scan("01JAN2007 31JAN2007 1.111..")));
		assertEquals(2, (int)monthColumn.getValue(intervalScanner.scan("01MAR2008 28MAR2008 1.111..")));
	}
}
