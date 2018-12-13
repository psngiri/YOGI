package yogi.report.dated.columns.date.test;

import junit.framework.TestCase;

import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.date.DateColumn;

public class DateColumnTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testGetValue()
	{
		DateColumn dateColumn = new DateColumn();
		assertEquals("01JAN2007", dateColumn.getValue(intervalScanner.scan("01JAN2007 01JAN2007 1.111..")).toString());
		assertEquals("01MAR2008", dateColumn.getValue(intervalScanner.scan("01MAR2008 01MAR2008 1.111..")).toString());
	}
}
