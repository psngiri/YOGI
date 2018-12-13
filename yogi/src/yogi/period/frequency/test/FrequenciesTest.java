package yogi.period.frequency.test;

import java.util.List;

import junit.framework.TestCase;

import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;
import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;
import yogi.period.frequency.io.MondayToSundayFrequencyScanner;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;
import yogi.period.time.TimeManager;
import yogi.period.time.io.HHMMTimeScanner;
import yogi.period.time.range.TimeRange;

public class FrequenciesTest extends TestCase
{
	MondayToSundayFrequencyScanner frequencyScanner = new MondayToSundayFrequencyScanner();
	MondayToSundayFrequencyFormatter frequencyFormatter = new MondayToSundayFrequencyFormatter();

	public void testShiftForward()
	{
		assertEquals("1001010", frequencyFormatter.format(Frequencies.shiftForward(frequencyScanner.scan("..1.1.1").create(), 1)));
		assertEquals("1111011", frequencyFormatter.format(Frequencies.shiftForward(frequencyScanner.scan("111.111").create(), 1)));
	}

	public void testShiftBackward()
	{
		assertEquals("0010101", frequencyFormatter.format(Frequencies.shiftBackward(frequencyScanner.scan("1..1.1.").create(), 1)));
		assertEquals("1110111", frequencyFormatter.format(Frequencies.shiftBackward(frequencyScanner.scan("1111.11").create(), 1)));
	}

	public void testShift()
	{
		for (Frequency frequency : FrequencyManager.get().findAll())
		{
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 1), -1));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 2), -2));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 3), -3));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 4), -4));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 5), -5));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 6), -6));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 7), -7));
			assertEquals(frequency, Frequencies.shift(Frequencies.shift(frequency, 8), -8));
		}
	}

	public void testNumberOfDays()
	{
		assertEquals(3, Frequencies.getNumberOfDays(frequencyScanner.scan("..1.1.1").create()));
		assertEquals(4, Frequencies.getNumberOfDays(frequencyScanner.scan("1.1.1.1").create()));
		assertEquals(7, Frequencies.getNumberOfDays(frequencyScanner.scan("1111111").create()));
		assertEquals(2, Frequencies.getNumberOfDays(frequencyScanner.scan(".....11").create()));
		assertEquals(5, Frequencies.getNumberOfDays(frequencyScanner.scan("11111..").create()));
	}

	public void testGetOperationalDays()
	{
		List<Integer> operationalDays = Frequencies.getOperationalDays(frequencyScanner.scan("..1.1.1").create());
		assertEquals(3, operationalDays.size());
		assertEquals(1, operationalDays.get(0).intValue());
		assertEquals(4, operationalDays.get(1).intValue());
		assertEquals(6, operationalDays.get(2).intValue());
	}
	
	public void testContainsRange()
	{
		
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'R', 'F', 'J', 'S'});
		TimeRange timeRange = new TimeRange(new HHMMTimeScanner().scan("0000").create(), new HHMMTimeScanner().scan("1930").create());
		
		TimeRange timeRange1 = new TimeRange(new HHMMTimeScanner().scan("2001").create(), new HHMMTimeScanner().scan("1559").create());
		assertFalse(Frequencies.contains(scanner.scan("RF").create(), timeRange1, scanner.scan("F").create(), new HHMMTimeScanner().scan("1925").create(), true));
	
	
		
		
		//with TODAPPL.R
		assertTrue(Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("M").create(), new HHMMTimeScanner().scan("0000").create(), true));
		assertTrue(Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("T").create(), new HHMMTimeScanner().scan("0001").create(), true));
		assertFalse(Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("W").create(), new HHMMTimeScanner().scan("1931").create(), true));
		assertTrue(Frequencies.contains(scanner.scan("MTW...S").create(), timeRange, 
				scanner.scan("S").create(), new HHMMTimeScanner().scan("0000").create(), true));
		assertFalse(Frequencies.contains(scanner.scan("MTW...S").create(), timeRange, 
				scanner.scan("J").create(), new HHMMTimeScanner().scan("1931").create(), true));
		
		//without TODAPPL.D
		assertTrue(Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("T").create(), new HHMMTimeScanner().scan("0001").create(), false));
		assertFalse(Frequencies.contains(scanner.scan("MTW..S").create(), timeRange, 
				scanner.scan("R").create(), new HHMMTimeScanner().scan("1930").create(), false));
		assertFalse(Frequencies.contains(scanner.scan("MTW..S").create(), timeRange, 
				scanner.scan("W").create(), new HHMMTimeScanner().scan("1931").create(), false));
		
		
		
		
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("M").create(), new HHMMTimeScanner().scan("0000").create(), true));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("T").create(), new HHMMTimeScanner().scan("0001").create(), true));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("W").create(), new HHMMTimeScanner().scan("1931").create(), true));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW...S").create(), timeRange, 
				scanner.scan("J").create(), new HHMMTimeScanner().scan("1931").create(), true));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW").create(), timeRange, 
				scanner.scan("T").create(), new HHMMTimeScanner().scan("0001").create(), false));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW..S").create(), timeRange, 
				scanner.scan("Q").create(), new HHMMTimeScanner().scan("1930").create(), false));
		System.out.println("contains: "+ Frequencies.contains(scanner.scan("MTW..S").create(), timeRange, 
				scanner.scan("W").create(), new HHMMTimeScanner().scan("1931").create(), false));
		
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), true));
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), true));
		
		timeRange = new TimeRange(new HHMMTimeScanner().scan("2359").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), true));
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("1000").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("0900").create(), true));
			
		timeRange = new TimeRange(new HHMMTimeScanner().scan("2359").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("J").create(), new HHMMTimeScanner().scan("2205").create(), true));
	
		timeRange = new TimeRange(TimeManager.MinTime,  new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), true));

		timeRange = new TimeRange(new HHMMTimeScanner().scan("1000").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("J").create(), new HHMMTimeScanner().scan("0900").create(), true));
		
		timeRange = new TimeRange(new HHMMTimeScanner().scan("1000").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("1000").create(), true));
	
		timeRange = new TimeRange(TimeManager.MinTime, new HHMMTimeScanner().scan("0200").create());
		assertFalse(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("0300").create(), true));
	
		
		//Day
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), false));
		
	    timeRange = new TimeRange(new HHMMTimeScanner().scan("2359").create(), new HHMMTimeScanner().scan("2359").create());
		assertFalse(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("1000").create(), new HHMMTimeScanner().scan("2359").create());
		assertFalse(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("0900").create(), false));
	
		timeRange = new TimeRange(TimeManager.MinTime, TimeManager.get().getTime(1439));
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
		timeRange = new TimeRange(TimeManager.MinTime, TimeManager.get().getTime(1439));
		assertTrue(Frequencies.contains(scanner.scan("FJS").create(), timeRange, scanner.scan("S").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
	//Neena testcases
		
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertFalse(Frequencies.contains(scanner.scan("TWJ").create(), timeRange, scanner.scan("R").create(), new HHMMTimeScanner().scan("2205").create(), false));
		
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("TWJ").create(), timeRange, scanner.scan("J").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
		timeRange = new TimeRange(new HHMMTimeScanner().scan("0001").create(), new HHMMTimeScanner().scan("2359").create());
		assertTrue(Frequencies.contains(scanner.scan("TWJ").create(), timeRange, scanner.scan("J").create(), new HHMMTimeScanner().scan("2205").create(), false));
	
	}
	
}