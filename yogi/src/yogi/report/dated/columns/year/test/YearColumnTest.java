package yogi.report.dated.columns.year.test;

import junit.framework.TestCase;

import yogi.period.interval.io.IntervalScanner;
import yogi.report.dated.columns.year.YearColumn;

public class YearColumnTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testGetValue() {
		YearColumn<Void> yearColumn = new YearColumn<Void>();
		assertEquals(2007, (int) yearColumn.getValue(intervalScanner.scan("01JAN2007 31JAN2007 1.111..")));
		assertEquals(2008, (int) yearColumn.getValue(intervalScanner.scan("01MAR2008 28MAR2008 1.111..")));
	}
}
