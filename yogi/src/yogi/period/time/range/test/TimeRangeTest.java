package yogi.period.time.range.test;

import yogi.period.time.TimeManager;
import yogi.period.time.range.TimeRange;

import junit.framework.TestCase;

public class TimeRangeTest extends TestCase {

	public void testSimpleRange() {
		TimeRange range = new TimeRange(TimeManager.get().getTime(360), TimeManager.get().getTime(600));
		assertFalse(range.isOverMidnight());
		assertTrue(range.contains(TimeManager.get().getTime(420)));
		assertFalse(range.contains(TimeManager.get().getTime(300)));
		assertTrue(range.contains(TimeManager.get().getTime(360)));
		assertTrue(range.contains(TimeManager.get().getTime(600)));
	}
	
	public void testSimpleRangeWithBoundariesExcluded() {
		TimeRange range = new TimeRange(TimeManager.get().getTime(360), false, TimeManager.get().getTime(600), false);
		assertFalse(range.isOverMidnight());
		assertTrue(range.contains(TimeManager.get().getTime(420)));
		assertFalse(range.contains(TimeManager.get().getTime(300)));
		assertFalse(range.contains(TimeManager.get().getTime(360)));
		assertFalse(range.contains(TimeManager.get().getTime(600)));
	}

	public void testOverMidnightRange() {
		TimeRange range = new TimeRange(TimeManager.get().getTime(1300), TimeManager.get().getTime(600));
		assertTrue(range.contains(TimeManager.get().getTime(420)));
		assertFalse(range.contains(TimeManager.get().getTime(1299)));
		assertFalse(range.contains(TimeManager.get().getTime(601)));
		assertTrue(range.contains(TimeManager.get().getTime(1340)));
		assertTrue(range.contains(TimeManager.get().getTime(1440)));
		assertTrue(range.contains(TimeManager.get().getTime(1300)));
		assertTrue(range.contains(TimeManager.get().getTime(600)));
		assertTrue(range.isOverMidnight());
	}
	
	public void testRangeOverlap() {
	    TimeRange omRange1 = new TimeRange(TimeManager.get().getTime(1386), TimeManager.get().getTime(179));
	    TimeRange omRange2 = new TimeRange(TimeManager.get().getTime(1248), TimeManager.get().getTime(179));
		assertTrue(omRange1.isOverMidnight());
		assertTrue(omRange2.isOverMidnight());
	    assertTrue(omRange1.intersects(omRange2));
	    assertTrue(omRange2.intersects(omRange1));
	    
	    omRange2 = new TimeRange(TimeManager.get().getTime(180), TimeManager.get().getTime(540));
	    assertFalse(omRange1.intersects(omRange2));
	    assertFalse(omRange2.intersects(omRange1));
	    
	    omRange1 = new TimeRange(TimeManager.get().getTime(1386), TimeManager.get().getTime(1439));
	    omRange2 = new TimeRange(TimeManager.get().getTime(1400), TimeManager.get().getTime(179));
	    assertTrue(omRange1.intersects(omRange2));
	    assertTrue(omRange2.intersects(omRange1));
	}
	
	public void testMidnight() {
		TimeRange range1 = new TimeRange(TimeManager.get().getTime(0), TimeManager.get().getTime(1439));
		assertTrue(range1.contains(TimeManager.get().getTime(1440)));
		
		range1 = new TimeRange(TimeManager.get().getTime(1440), TimeManager.get().getTime(1440));
		assertTrue(range1.contains(TimeManager.get().getTime(1440)));
		
		range1 = new TimeRange(TimeManager.get().getTime(900), TimeManager.get().getTime(1440));
		assertTrue(range1.contains(TimeManager.get().getTime(1440)));
		
		range1 = new TimeRange(TimeManager.get().getTime(1440), TimeManager.get().getTime(900));
		assertTrue(range1.contains(TimeManager.get().getTime(1440)));
	}
}
