package yogi.period.frequency.io.test;

import junit.framework.TestCase;

import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;

public class MondayToSundayOnlyOnCharFrequencyScannerTest extends TestCase {
	
	public void testFormat1()
	{
		char[] onCharacters = new char[]{'M','T','W','Q','F','J','S'};
		MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(onCharacters);
		
		String customRecord = "";

		customRecord = "MTWQFJS";
		assertEquals(127, scanner.scan(customRecord).create().getValue());
		
		customRecord = "MTWQFJ";
		assertEquals(63, scanner.scan(customRecord).create().getValue());
		
		customRecord = "TWQFJ";
		assertEquals(31, scanner.scan(customRecord).create().getValue());
		
		customRecord = "WQFJ";
		assertEquals(15, scanner.scan(customRecord).create().getValue());

		customRecord = "QFJ";
		assertEquals(7, scanner.scan(customRecord).create().getValue());
		
		customRecord = "FJ";
		assertEquals(3, scanner.scan(customRecord).create().getValue());
		
		customRecord = "J";
		assertEquals(1, scanner.scan(customRecord).create().getValue());
		
		customRecord = "";
		assertEquals(0, scanner.scan(customRecord).create().getValue());
	}
	
}
