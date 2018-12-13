package yogi.period.frequency.io.test;

import junit.framework.TestCase;

import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.MondayToSundayFrequencyFormatter;
import yogi.period.frequency.io.MondayToSundayOptionalOffCharFrequencyScanner;

public class MondayToSundayFrequencyFormatterTest extends TestCase {
	public void testMTSFormat()
	{
	 MondayToSundayFrequencyFormatter formatter = new MondayToSundayFrequencyFormatter('.', new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'}, true);
	 MondayToSundayOptionalOffCharFrequencyScanner scanner = new MondayToSundayOptionalOffCharFrequencyScanner(new char[]{'M', 'T', 'W', 'Q', 'F', 'J', 'S'});
	 Frequency f1 = scanner.scan("MTW.FJ").create();
	 Frequency f2 = scanner.scan(".TW.FJS").create();
	 Frequency f3 = scanner.scan(".T..FJ").create();
	 Frequency f4 = scanner.scan("....F.").create();
	 Frequency f5 = scanner.scan("......").create();
	 assertEquals("MTWFJ",formatter.format(f1));
	 assertEquals("TWFJS",formatter.format(f2));
	 assertEquals("TFJ",formatter.format(f3));
	 assertEquals("F",formatter.format(f4));
	 assertEquals("",formatter.format(f5));
	}
	

}
