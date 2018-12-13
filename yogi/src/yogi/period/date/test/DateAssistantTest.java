package yogi.period.date.test;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateAssistant;
import yogi.period.date.io.DDMMMYYYYDateFormatter;
import yogi.period.date.io.DDMMMYYYYDateScanner;
import yogi.period.frequency.io.MondayToSundayFrequencyScanner;

public class DateAssistantTest extends TestCase {
	DDMMMYYYYDateScanner dateScanner = new DDMMMYYYYDateScanner();
	DDMMMYYYYDateFormatter dateFormatter = new DDMMMYYYYDateFormatter();
	MondayToSundayFrequencyScanner frequencyScanner = new MondayToSundayFrequencyScanner();

	public void testAdjustDateForward()
	{
		Date date = dateScanner.scan("05SEP2007").create();
		assertEquals("05SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateForward(date, 
						frequencyScanner.scan("..1..11").create())));
		assertEquals("08SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateForward(date, 
						frequencyScanner.scan(".....11").create())));
		assertEquals("11SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateForward(date, 
						frequencyScanner.scan(".1.....").create())));
	}
	public void testAdjustDateBackward()
	{
		Date date = dateScanner.scan("05SEP2007").create();
		assertEquals("05SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateBackward(date, 
						frequencyScanner.scan("..1..11").create())));
		assertEquals("02SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateBackward(date, 
						frequencyScanner.scan(".....11").create())));
		assertEquals("04SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateBackward(date, 
						frequencyScanner.scan(".1.....").create())));
		assertEquals("30AUG2007", dateFormatter.format(
				DateAssistant.get().adjustDateBackward(date, 
						frequencyScanner.scan("...1...").create())));
		assertEquals("01SEP2007", dateFormatter.format(
				DateAssistant.get().adjustDateBackward(date, 
						frequencyScanner.scan("...1.1.").create())));
	}
	
}
