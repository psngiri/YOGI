package yogi.period.frequency.io.test;

import yogi.period.frequency.Frequency;
import yogi.period.frequency.io.SundayToSaturdayFrequencyFormatter;
import yogi.period.frequency.io.SundayToSaturdayFrequencyScanner;

import junit.framework.TestCase;

public class SundayToSaturdayFrequencyFormatterTest extends TestCase {

	public void testFormat()
	{
		SundayToSaturdayFrequencyScanner scanner = new SundayToSaturdayFrequencyScanner();
		SundayToSaturdayFrequencyFormatter formatter = new SundayToSaturdayFrequencyFormatter();
		assertFrequency(scanner,formatter,"0100110",38);
		assertFrequency(scanner,formatter,"1111111",127);
		assertFrequency(scanner,formatter,"0000000",0);
		assertFrequency(scanner,formatter,"1000001",65);
	}

	public void testFormat1()
	{
		char[] onCharacters = new char[]{'S','M','T','W','T','F','S'};
		SundayToSaturdayFrequencyScanner scanner = new SundayToSaturdayFrequencyScanner(onCharacters);
		SundayToSaturdayFrequencyFormatter formatter = new SundayToSaturdayFrequencyFormatter('.', onCharacters);
		assertFrequency(scanner,formatter,".M..TF.",38);
		assertFrequency(scanner,formatter,"SMTWTFS",127);
		assertFrequency(scanner,formatter,".......",0);
		assertFrequency(scanner,formatter,"S.....S",65);
	}
	private void assertFrequency(SundayToSaturdayFrequencyScanner scanner,SundayToSaturdayFrequencyFormatter formatter,String freq,int freqValue){Frequency frequency = scanner.scan(freq).create();
	assertEquals(freqValue, frequency.getValue());
	assertEquals(freq, formatter.format(frequency));}
}
