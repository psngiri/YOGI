package yogi.period.interval.data.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.data.IntervaledData;
import yogi.period.interval.io.IntervalScanner;

public class IntervaledDataTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	public void testGetData()
	{
		IntervaledData<Integer> intervaledData = new IntervaledData<Integer>(1);
		assertEquals(1, intervaledData.getData().intValue());
	}
	
	public void testGetIntervals() throws Exception
	{
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15APR2007 1111111");
		List<Interval> intervals = Intervals.add(interval1, interval2);
		IntervaledData<Integer> intervaledData = new IntervaledData<Integer>(1, intervals);
		Intervals.areIntervalsEqual(intervaledData.getIntervals(), intervals);

	}
}
