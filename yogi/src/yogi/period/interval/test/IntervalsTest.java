package yogi.period.interval.test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.interval.EmptyIntervalException;
import yogi.period.interval.Interval;
import yogi.period.interval.Intervals;
import yogi.period.interval.io.IntervalScanner;

public class IntervalsTest extends TestCase {
	private IntervalScanner intervalScanner = new IntervalScanner();

	/*
	 * Test method for 'yogi.period.interval.Intervals.union(Interval, Interval)'
	 */
	public void testUnion() {
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15APR2007 1111111");

		assertTrue(!interval1.intersects(interval2));
		try {
			Intervals.union(interval1, interval2);
			fail();
		} catch (RuntimeException e) {
		}
	}
	
	public void testNormalize() throws EmptyIntervalException {
		Interval interval1 = intervalScanner.scan("20NOV2017 24NOV2017 .1.1111");
		Interval interval2 = intervalScanner.scan("21NOV2017 23NOV2017 ...1...");
		Interval interval3 = intervalScanner.scan("11NOV2017 30NOV2017 1111111");

		interval1.normalize();
		System.out.println(interval1);
		assertEquals(intervalScanner.scan("21NOV2017 24NOV2017 .1.11.."), interval1);
		interval2.normalize();
		System.out.println(interval2);
		assertEquals(intervalScanner.scan("23NOV2017 23NOV2017 ...1..."), interval2);
		interval3.normalize();
		System.out.println(interval3);
		assertEquals(intervalScanner.scan("11NOV2017 30NOV2017 1111111"), interval3);
	}
	
	public void testAdd()
	{
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15APR2007 1111111");

		List<Interval> add = Intervals.add(interval1, interval2);
		assertEquals(2, add.size());
		assertEquals(interval1, add.get(0));
		assertEquals(interval2, add.get(1));
		Interval interval3 = intervalScanner.scan("01FEB2007 15MAY2007 .....11");
		
		add = Intervals.add(interval1, interval3);
		Interval interval4 = intervalScanner.scan("16MAR2007 15MAY2007 .....11");
		assertEquals(2, add.size());
		assertEquals(interval1, add.get(0));
		assertEquals(interval4, add.get(1));
	}
	
	public void testUnion1()
	{
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01APR2007 15APR2007 1111111");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {
			
		}
		
		
	}
	
	public void testUnionAdjacent()
	{
		Interval interval1 = intervalScanner.scan("01JAN2008 02JAN2008 .11....");
		Interval interval2 = intervalScanner.scan("05JAN2008 06JAN2008 .....11");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("01JAN2008 06JAN2008 .TW..JS",union.toString());
		
	}
	
	public void testUnionAdjacentNormalize()
	{
		Interval interval1 = intervalScanner.scan("01JAN2008 06JAN2008 .11....");
		Interval interval2 = intervalScanner.scan("03JAN2008 06JAN2008 .....11");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("01JAN2008 06JAN2008 .TW..JS",union.toString());
		
	}
	
	public void testUnionAdjacentNormalize1()
	{
		Interval interval2 = intervalScanner.scan("01JAN2008 06JAN2008 .111...");
		Interval interval1 = intervalScanner.scan("03JAN2008 08JAN2008 11...11");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("01JAN2008 08JAN2008 MTWR.JS",union.toString());
		
	}
	
	/*public void testUnionAdjacentNormalizeFail()
	{
		Interval interval2 = intervalScanner.scan("01JAN2008 06JAN2008 .111...");
		Interval interval1 = intervalScanner.scan("03JAN2008 09JAN2008 111..11");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("01JAN2008 08JAN2008 MTWH.AS",union.toString());	
	}*/
	
	public void testUnionContigous()
	{
		Interval interval1 = intervalScanner.scan("01JAN2008 02JAN2008 .11....");
		Interval interval2 = intervalScanner.scan("08JAN2008 09JAN2008 .11....");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("01JAN2008 09JAN2008 .TW....",union.toString());
		
		interval1 = intervalScanner.scan("02SEP2007 07SEP2007 11111.1");
		interval2 = intervalScanner.scan("08SEP2007 09SEP2007 .....11");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("02SEP2007 09SEP2007 MTWRFJS", union.toString());
		
		interval1 = intervalScanner.scan("01SEP2007 30SEP2007 111111.");
		interval2 = intervalScanner.scan("01OCT2007 15OCT2007 111111.");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("01SEP2007 15OCT2007 MTWRFJ.", union.toString());
		
	}
	
	public void testUnionContigousWithoutNormalizing()
	{
		Interval interval1 = intervalScanner.scan("01JAN2008 05JAN2008 .11....");
		Interval interval2 = intervalScanner.scan("08JAN2008 09JAN2008 .11....");
		
		Interval union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("01JAN2008 09JAN2008 .TW....",union.toString());
		
		interval1 = intervalScanner.scan("02SEP2007 07SEP2007 11111.1");
		interval2 = intervalScanner.scan("08SEP2007 09SEP2007 .....11");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("02SEP2007 09SEP2007 MTWRFJS", union.toString());
		
		interval1 = intervalScanner.scan("01SEP2007 30SEP2007 111111.");
		interval2 = intervalScanner.scan("01OCT2007 15OCT2007 111111.");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("01SEP2007 15OCT2007 MTWRFJ.", union.toString());
		
	}
	
	public void testUnionDateRangeIntersection()
	{
		Interval interval1 = intervalScanner.scan("01SEP2007 15SEP2007 11111.1");
		Interval interval2 = intervalScanner.scan("12SEP2007 30SEP2007 11111.1");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("02SEP2007 30SEP2007 MTWRF.S",union.toString());
		
		interval1 = intervalScanner.scan("01SEP2007 15SEP2007 11111.1");
		interval2 = intervalScanner.scan("12SEP2007 30SEP2007 11111..");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
		
		interval1 = intervalScanner.scan("01SEP2007 19SEP2007 1111111");
		interval2 = intervalScanner.scan("18SEP2007 22SEP2007 111111.");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("01SEP2007 22SEP2007 MTWRFJS", union.toString());
	}
	
	
	public void testUnionFalse()
	{
		Interval interval1 = intervalScanner.scan("01JAN2000 30JUN2000 .1..11.");
		Interval interval2 = intervalScanner.scan("01MAR2000 21DEC2000 .1.1.1.");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
		
		interval1 = intervalScanner.scan("01JAN2000 30JUN2000 .1..11.");
		interval2 = intervalScanner.scan("01JUL2000 21DEC2000 .1.1.1.");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
		
		interval1 = intervalScanner.scan("01SEP2007 30SEP2007 1111111");
		interval2 = intervalScanner.scan("04OCT2007 11OCT2007 1111111");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
		
		interval1 = intervalScanner.scan("01SEP2007 30SEP2007 111111.");
		interval2 = intervalScanner.scan("04OCT2007 11OCT2007 1111111");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
		
		interval1 = intervalScanner.scan("01SEP2007 08SEP2007 111111.");
		interval2 = intervalScanner.scan("09SEP2007 15SEP2007 1111111");
		
		try {
			Intervals.union(interval1, interval2);
			fail("Expected to fail in the union method");
		} catch (RuntimeException e) {}	
	}

	public void testDates()
	{
		try {
			intervalScanner.scan("01SEP2007 31AUG2007 1111111");
			fail("Expected to fail in the DateRange constructor");
		} catch (RuntimeException e) {		}
	}
	/*
	 * Test method for 'yogi.period.interval.Intervals.intersect(Interval, Interval)'
	 */
	public void testIntersection() {
		Interval interval1 = intervalScanner.scan("01FEB2007 15MAR2007 1111111");
		Interval interval2 = intervalScanner.scan("01MAR2007 15APR2007 11111..");
		Interval interval3 = intervalScanner.scan("01MAR2007 15MAR2007 11111..");
		assertEquals(interval3, Intervals.intersection(interval1, interval2));
		List<Interval> intervals1 = new ArrayList<Interval>();
		List<Interval> intervals2 = new ArrayList<Interval>();
		intervals1.add(interval1);
		intervals1.add(interval2);
		Interval interval4 = intervalScanner.scan("01FEB2007 01APR2007 ....111");
		List<Interval> intersection = Intervals.intersection(intervals1, interval4);
		assertEquals(2, intersection.size());
		assertEquals("01FEB2007 15MAR2007 ....FJS",  intersection.get(0).toString());
		assertEquals("16MAR2007 01APR2007 ....F..",  intersection.get(1).toString());
		intervals2.add(interval3);
		intervals2.add(interval4);
		intersection = Intervals.intersection(intervals1, intervals2);
		assertEquals(4, intersection.size());
		assertEquals("01MAR2007 15MAR2007 MTWRF..",  intersection.get(0).toString());
		assertEquals("01FEB2007 28FEB2007 ....FJS",  intersection.get(1).toString());
		assertEquals("01MAR2007 15MAR2007 .....JS",  intersection.get(2).toString());
		assertEquals("16MAR2007 01APR2007 ....F..",  intersection.get(3).toString());
	}
	
	public void testShift()
	{
		Interval interval = intervalScanner.scan("01MAR2007 15APR2007 11111..");
		assertEquals("02MAR2007 16APR2007 .TWRFJ.", Intervals.shift(interval, 1).toString());
		assertEquals("28FEB2007 14APR2007 MTWR..S", Intervals.shift(interval, -1).toString());
	}

	/*
	 * Test method for 'yogi.period.interval.Intervals.subtract(Interval, Interval)'
	 */
	public void testSubtract() {
		Interval interval1 = intervalScanner.scan("31JAN2007 01MAR2007 1111...");
		Interval interval2 = intervalScanner.scan("31JAN2007 15MAR2007 .1111..");
		List<Interval> subtractedIntervals = Intervals.subtract(interval1, interval2);
		assertEquals(1, subtractedIntervals.size());
		assertEquals("31JAN2007 01MAR2007 M......",  subtractedIntervals.get(0).toString());
		
	}

	/*
	 * Test method for 'yogi.period.interval.Intervals.minus(List<Interval>, List<Interval>)'
	 */
	public void testMinus() {

	}

	/*
	 * Test method for 'yogi.period.interval.Intervals.getDays(Interval)'
	 */
	public void testGetDaysSorted() {
		Interval interval = intervalScanner.scan("13JUN2007 10JUL2007 .1.1.1.");
		List<Date> days = Intervals.getDaysSorted(interval);
		ListIterator<Date> listIterator = days.listIterator();
		Date previous = listIterator.next();
		while(listIterator.hasNext())
		{
			Date next = listIterator.next();
			assertTrue(next.getValue() - previous.getValue() > 0);
			previous = next;
		}
	}

	public void testCompress()
	{
		//22DEC2007 23JAN2008 MT.HF..
		//22DEC2007 19JAN2008 .....A.
	}
	
	public void testUnionA()
	{
		Interval interval1 = intervalScanner.scan("04OCT2008 24OCT2008 111111.");
		Interval interval2 = intervalScanner.scan("25OCT2008 25OCT2008 .....1.");
		
		Interval union = Intervals.union(interval1, interval2);
		assertEquals("04OCT2008 25OCT2008 MTWRFJ.",union.toString());
		
		interval1 = intervalScanner.scan("05JUL2008 02OCT2008 ..11.1.");
		interval2 = intervalScanner.scan("04JUL2008 03OCT2008 ....1..");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("04JUL2008 03OCT2008 ..WRFJ.", union.toString());
		
		interval1 = intervalScanner.scan("01DEC2007 04APR2008 111111.");
		interval2 = intervalScanner.scan("05APR2008 05APR2008 .....1.");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("01DEC2007 05APR2008 MTWRFJ.", union.toString());
		
		interval1 = intervalScanner.scan("06APR2008 30JUN2008 1111111");
		interval2 = intervalScanner.scan("01JUL2008 25OCT2008 1111111");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("06APR2008 25OCT2008 MTWRFJS", union.toString());
		Interval interval3 = intervalScanner.scan("01JAN2008 05APR2008 1111111");
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		
		
		
		intervals.add(interval3);
		
		List<Interval> compressedIntervals = Intervals.compress(intervals);
		assertEquals(1, compressedIntervals.size());
		
		interval1 = intervalScanner.scan("15JUN2008 12OCT2008 ......1");
		interval2 = intervalScanner.scan("19OCT2008 19OCT2008 ......1");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("15JUN2008 19OCT2008 ......S", union.toString());
		
		interval1 = intervalScanner.scan("09JUN2008 18OCT2008 111111.");
		interval2 = intervalScanner.scan("20OCT2008 25OCT2008 111111.");
		interval3 = intervalScanner.scan("15JUN2008 19OCT2008 ......1");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("09JUN2008 25OCT2008 MTWRFJ.", union.toString());
		
		intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		intervals.add(interval3);
		
		compressedIntervals = Intervals.compress(intervals);
		assertEquals(1, compressedIntervals.size());
		
		interval1 = intervalScanner.scan("07JUL2008 06OCT2008 1......");
		interval2 = intervalScanner.scan("01JUL2008 05OCT2008 .111111");
		
		union = Intervals.union(interval1, interval2);
		assertEquals("01JUL2008 06OCT2008 MTWRFJS", union.toString());
		
		interval1 = intervalScanner.scan("05NOV2007 23NOV2007 11111..");
		interval2 = intervalScanner.scan("25NOV2007 25NOV2007 ......1");
		interval3 = intervalScanner.scan("26NOV2007 07MAR2008 11111..");
		
		intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		intervals.add(interval3);
		
		union = Intervals.union(interval1, interval3);
		assertEquals("05NOV2007 07MAR2008 MTWRF..", union.toString());
		
		
		try {
			union = Intervals.union(union, interval2);
			fail("Expected to fail in Union Method");
		} catch (RuntimeException e) {
			
		}
				
		compressedIntervals = Intervals.compress(intervals);
		assertEquals(2, compressedIntervals.size());
		
		try {
			union = Intervals.union(interval1, interval2);
			fail("Expected to fail in Union Method");
		} catch (RuntimeException e) {
	
		}
		interval1 = intervalScanner.scan("29FEB2009 06MAR2009 111....");
		interval2 = intervalScanner.scan("06MAR2009 06MAR2009 ..1....");
		union = Intervals.union(interval1, interval2);
		assertEquals("02MAR2009 04MAR2009 MTW....", union.toString());
	}
	
	public void testUnionWithoutNormalizingA()
	{
		Interval interval1 = intervalScanner.scan("04OCT2008 24OCT2008 111111.");
		Interval interval2 = intervalScanner.scan("25OCT2008 25OCT2008 .....1.");
		
		Interval union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("04OCT2008 25OCT2008 MTWRFJ.",union.toString());
		
		interval1 = intervalScanner.scan("05JUL2008 02OCT2008 ..11.1.");
		interval2 = intervalScanner.scan("04JUL2008 03OCT2008 ....1..");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("04JUL2008 03OCT2008 ..WRFJ.", union.toString());
		
		interval1 = intervalScanner.scan("01DEC2007 04APR2008 111111.");
		interval2 = intervalScanner.scan("05APR2008 05APR2008 .....1.");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("01DEC2007 05APR2008 MTWRFJ.", union.toString());
		
		interval1 = intervalScanner.scan("06APR2008 30JUN2008 1111111");
		interval2 = intervalScanner.scan("01JUL2008 25OCT2008 1111111");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("06APR2008 25OCT2008 MTWRFJS", union.toString());
		Interval interval3 = intervalScanner.scan("01JAN2008 05APR2008 1111111");
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		
		
		
		intervals.add(interval3);
		
		List<Interval> compressedIntervals = Intervals.compressWithOutNormalizing(intervals);
		assertEquals(1, compressedIntervals.size());
		
		interval1 = intervalScanner.scan("15JUN2008 12OCT2008 ......1");
		interval2 = intervalScanner.scan("19OCT2008 19OCT2008 ......1");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("15JUN2008 19OCT2008 ......S", union.toString());
		
		interval1 = intervalScanner.scan("09JUN2008 18OCT2008 111111.");
		interval2 = intervalScanner.scan("20OCT2008 25OCT2008 111111.");
		interval3 = intervalScanner.scan("15JUN2008 19OCT2008 ......1");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("09JUN2008 25OCT2008 MTWRFJ.", union.toString());
		
		intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		intervals.add(interval3);
		
		compressedIntervals = Intervals.compressWithOutNormalizing(intervals);
		assertEquals(1, compressedIntervals.size());
		
		interval1 = intervalScanner.scan("07JUL2008 06OCT2008 1......");
		interval2 = intervalScanner.scan("01JUL2008 05OCT2008 .111111");
		
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("01JUL2008 06OCT2008 MTWRFJS", union.toString());
		
		interval1 = intervalScanner.scan("05NOV2007 23NOV2007 11111..");
		interval2 = intervalScanner.scan("25NOV2007 25NOV2007 ......1");
		interval3 = intervalScanner.scan("26NOV2007 07MAR2008 11111..");
		
		intervals = new ArrayList<Interval>();
		intervals.add(interval1);
		intervals.add(interval2);
		intervals.add(interval3);
		
		union = Intervals.unionWithoutNormalizing(interval1, interval3);
		assertEquals("05NOV2007 07MAR2008 MTWRF..", union.toString());
		
		
		try {
			union = Intervals.unionWithoutNormalizing(union, interval2);
			fail("Expected to fail in Union Method");
		} catch (RuntimeException e) {
			
		}
				
		compressedIntervals = Intervals.compressWithOutNormalizing(intervals);
		assertEquals(2, compressedIntervals.size());
		
		try {
			union = Intervals.unionWithoutNormalizing(interval1, interval2);
			fail("Expected to fail in Union Method");
		} catch (RuntimeException e) {
	
		}
		interval1 = intervalScanner.scan("29FEB2009 06MAR2009 111....");
		interval2 = intervalScanner.scan("06MAR2009 06MAR2009 ..1....");
		union = Intervals.unionWithoutNormalizing(interval1, interval2);
		assertEquals("01MAR2009 06MAR2009 MTW....", union.toString());
	}
	
	public void testBreakIntervals()
	{
		Interval interval1 = intervalScanner.scan("01JAN2007 31JAN2007 1.111.1");
		Interval interval2 = intervalScanner.scan("05JAN2007 20JAN2007 1.....1");
		List<Interval> brokenIntervals = Intervals.breakInterval(interval1, interval2);
		assertEquals(4, brokenIntervals.size());
		assertTrue(Intervals.same(interval1, brokenIntervals));
//		System.out.println(brokenIntervals);
	}
	
	public void testBreakIntervals1()
	{
		Interval interval1 = intervalScanner.scan("01JAN2007 31JAN2007 1.111..");
		Interval interval2 = intervalScanner.scan("05JAN2007 20JAN2007 1.....1");
		List<Interval> brokenIntervals = Intervals.breakInterval(interval1, interval2);
		assertEquals(4, brokenIntervals.size());
		assertTrue(Intervals.same(interval1, brokenIntervals));
//		System.out.println(brokenIntervals);
	}
	
	public void testContains()
	{
		List<Interval> intervals1 = new ArrayList<Interval>();
		List<Interval> intervals2 = new ArrayList<Interval>();
		Interval interval1 = intervalScanner.scan("01JAN2007 31JAN2007 1.111.1");
		Interval interval2 = intervalScanner.scan("05JAN2007 20JAN2007 1.....1");
		intervals1.add(interval1);
		intervals2.add(interval2);
		assertTrue(Intervals.contains(intervals1, intervals2));
		assertTrue(Intervals.contains(intervals1, interval2));
	}
	
	public void testContains1()
	{
		List<Interval> intervals1 = new ArrayList<Interval>();
		List<Interval> intervals2 = new ArrayList<Interval>();
		Interval interval1 = intervalScanner.scan("01JAN2007 31JAN2007 1.111..");
		Interval interval2 = intervalScanner.scan("05JAN2007 20JAN2007 1.....1");
		intervals1.add(interval1);
		intervals2.add(interval2);
		assertTrue(!Intervals.contains(intervals1, intervals2));
		assertTrue(!Intervals.contains(intervals1, interval2));
	}
	
	public void testSubtractInifiniteDates()
	{
		Interval interval1 = intervalScanner.scan("01JAN2007 00XXX0000 1.111..");
		Interval interval2 = intervalScanner.scan("05JAN2007 00XXX0000 1.....1");
		List<Interval> subtract = Intervals.subtract(interval1, interval2);
		assertEquals(2, subtract.size());
		assertEquals("01JAN2007 04JAN2007 M.WRF..", subtract.get(0).toString());
		assertEquals("05JAN2007 00XXX0000 ..WRF..", subtract.get(1).toString());
		interval2 = intervalScanner.scan("05JAN2007 00XXX0000 1.111..");
		subtract = Intervals.subtract(interval1, interval2);
		assertEquals(1, subtract.size());
		assertEquals("01JAN2007 04JAN2007 M.WRF..", subtract.get(0).toString());
	}
	
	public void testGetNumberOfDays()
	{
		Interval interval = intervalScanner.scan("21NOV2007 30NOV2007 1111111");
		assertEquals(Intervals.getDays(interval).size(), Intervals.getNumberOfDays(interval));
		interval = intervalScanner.scan("21NOV2007 23NOV2007 1111111");
		assertEquals(Intervals.getDays(interval).size(), Intervals.getNumberOfDays(interval));
		interval = intervalScanner.scan("21NOV2007 23NOV2007 1.1.1.1");
		assertEquals(Intervals.getDays(interval).size(), Intervals.getNumberOfDays(interval));
		interval = intervalScanner.scan("21NOV2007 23MAR2008 1.1.1.1");
		assertEquals(Intervals.getDays(interval).size(), Intervals.getNumberOfDays(interval));
	}
	
	public List<Interval> compressTest(List<Interval> intervals)
	{
		List<Interval> rtnValue = Intervals.compress(intervals);
		assertDays(intervals, rtnValue);
		return rtnValue;
	}
	
	public void testOrderlyCompress()
	{
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(intervalScanner.scan("01DEC2008 15APR2009 0010001"));
		intervals.add(intervalScanner.scan("01JAN2009 01MAR2009 1110000"));
		intervals.add(intervalScanner.scan("01JAN2009 30MAR2009 1000111"));
		intervals.add(intervalScanner.scan("01JAN2009 30MAR2009 0001000"));
		intervals.add(intervalScanner.scan("01JUN2009 30JUN2009 1111001"));
		intervals.add(intervalScanner.scan("01JUL2009 30JUL2009 1111001"));

		List<Interval> orderlyCompress = orderlyCompressTest(intervals);
		System.out.println(orderlyCompress);
				
		
		intervals = new ArrayList<Interval>();
		intervals.add(intervalScanner.scan("05NOV2007 23NOV2007 11111.."));
		intervals.add(intervalScanner.scan("25NOV2007 25NOV2007 ......1"));
		intervals.add(intervalScanner.scan("26NOV2007 07MAR2008 11111.."));
		orderlyCompress = orderlyCompressTest(intervals);
		System.out.println(orderlyCompress);
		
		intervals = new ArrayList<Interval>();
		intervals.add(intervalScanner.scan("02NOV2008 11NOV2020 11111.1"));
		intervals.add(intervalScanner.scan("02NOV2008 11NOV2020 .....1."));
		orderlyCompress = orderlyCompressTest(intervals);
		System.out.println(orderlyCompress);
	}
	
	public void testOrderlyCompressTwo()
	{
		List<Interval> intervals = new ArrayList<Interval>();
		intervals.add(intervalScanner.scan("23FEB2010 05APR2010 1111101"));
		intervals.add(intervalScanner.scan("18FEB2010 05APR2010 1000001"));
		
		List<Interval> orderlyCompress = orderlyCompressWithoutNormalizingTest(intervals);
		System.out.println(orderlyCompress);
	}
	
	public List<Interval> orderlyCompressTest(List<Interval> intervals)
	{
		List<Interval> rtnValue = Intervals.orderlyCompress(intervals);
		assertDays(intervals, rtnValue);
		return rtnValue;
	}
	
	public List<Interval> orderlyCompressWithoutNormalizingTest(List<Interval> intervals)
	{
		List<Interval> rtnValue = Intervals.orderlyCompressWithoutNormalizing(intervals);
		assertDays(intervals, rtnValue);
		return rtnValue;
	}

	private void assertDays(List<Interval> intervals, List<Interval> intervals1) {
		try {
			Intervals.areIntervalsEqual(intervals, intervals1);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
