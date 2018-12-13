package yogi.period.interval.data.test;

import java.util.List;

import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.data.IntervalsObject;
import yogi.period.interval.io.IntervalScanner;

import junit.framework.TestCase;

public class IntervalsObjectTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	static class TestIntervalsObject extends IntervalsObject
	{

		protected TestIntervalsObject() {
			super();
		}

		protected TestIntervalsObject(List<Interval> intervals) {
			super(intervals);
		}

		@Override
		public void add(Interval interval) {
			super.add(interval);
		}

		@Override
		public void add(List<Interval> intervals) {
			super.add(intervals);
		}
		
	}
	
	public void testGetIntervals() throws Exception
	{
		TestIntervalsObject intervalsObject = new TestIntervalsObject();
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15APR2007 1111111");
		List<Interval> intervals = Intervals.add(interval1, interval2);
		
		intervalsObject.add(intervals);
		TestIntervalsObject intervalsObject1 = new TestIntervalsObject(intervals);
		Intervals.areIntervalsEqual(intervalsObject.getIntervals(), intervalsObject1.getIntervals());
		Interval interval3 = intervalScanner.scan("01MAR2007 25APR2007 1111...");
		intervalsObject.add(interval3);
		Intervals.add(intervals, interval3);
		Intervals.areIntervalsEqual(intervalsObject.getIntervals(), intervals);
		Interval interval4 = intervalScanner.scan("01MAR2007 25APR2007 ...1111");
		List<Interval> intervals1 = Intervals.add(interval3, interval4);
		intervalsObject.add(intervals1);
		Intervals.add(intervals, intervals1);
		Intervals.areIntervalsEqual(intervalsObject.getIntervals(), intervals);
	}
}
