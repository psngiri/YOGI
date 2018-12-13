package yogi.report.dated.columns.dow.test;

import junit.framework.TestCase;

import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.dow.DayOfWeekColumn;

public class DayOfWeekColumnTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testGetValue()
	{
		DayOfWeekColumn dayOfWeekColumn = new DayOfWeekColumn();
		assertEquals(6, (int)dayOfWeekColumn.getValue(intervalScanner.scan("03JAN2007 05JAN2007 ....1..")));
		assertEquals(2, (int)dayOfWeekColumn.getValue(intervalScanner.scan("01JAN2007 01JAN2007 1......")));
	}
	
	public void testFormat()
	{
		DayOfWeekColumn dayOfWeekColumn = new DayOfWeekColumn();
		assertEquals("FRI", dayOfWeekColumn.format(6));
		assertEquals("MON", dayOfWeekColumn.format(2));
	}
}
