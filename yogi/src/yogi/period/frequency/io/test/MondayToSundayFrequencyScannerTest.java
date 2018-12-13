package yogi.period.frequency.io.test;

import junit.framework.TestCase;

import yogi.period.frequency.io.MondayToSundayFrequencyScanner;

public class MondayToSundayFrequencyScannerTest extends TestCase {
	
	public void testFormat1()
	{
		char[] onCharacters = new char[]{'M','T','W','Q','F','J','S'};
		MondayToSundayFrequencyScanner scanner = new MondayToSundayFrequencyScanner(onCharacters);
		
		String record = "";
		
		record = "MTWQFJS";
		assertEquals(127, scanner.scan(record).create().getValue());
		
		record = "MTWQFJ.";
		assertEquals(63, scanner.scan(record).create().getValue());
		
		record = ".TWQFJ.";
		assertEquals(31, scanner.scan(record).create().getValue());
		
		record = "..WQFJ.";
		assertEquals(15, scanner.scan(record).create().getValue());
		
		record = "...QFJ.";
		assertEquals(7, scanner.scan(record).create().getValue());
		
		record = "....FJ.";
		assertEquals(3, scanner.scan(record).create().getValue());
		
		record = ".....J.";
		assertEquals(1, scanner.scan(record).create().getValue());
		
		record = ".......";
		assertEquals(0, scanner.scan(record).create().getValue());
	}
	
}
