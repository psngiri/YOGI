package yogi.period.frequency.test;

import java.util.Calendar;

import junit.framework.TestCase;

import yogi.period.date.Date;
import yogi.period.date.DateManager;
import yogi.period.frequency.Frequencies;
import yogi.period.frequency.Frequency;
import yogi.period.frequency.FrequencyManager;

public class FrequencyManagerTest extends TestCase {

	/*
	 * Test method for 'yogi.period.frequency.FrequencyManager.getFrequency(int)'
	public void testGetFrequencyInt() {

	}
	 */

	/*
	 * Test method for 'yogi.period.frequency.FrequencyManager.getFrequency(Date)'
	 */
	public void testGetFrequencyDate() {
		Date date = DateManager.get().getDate(2007, Calendar.MAY, 22);
		Frequency frequency = FrequencyManager.get().getFrequency(date);
		assertEquals(FrequencyManager.get().getFrequency(Frequency.TUESDAY), frequency);
				
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(0));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(1));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(2));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(3));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(4));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(5));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(6));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(7));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(8));
		System.out.println("Frequency : " + FrequencyManager.get().getFrequency(127));
		
		System.out.println("Equals : " + FrequencyManager.get().getFrequency(7).compareTo(FrequencyManager.get().getFrequency(7)));
		System.out.println("Intersects : " + Frequencies.intersects(FrequencyManager.get().getFrequency(3), FrequencyManager.get().getFrequency(1)));
		System.out.println("Intersection : " + Frequencies.intersection(FrequencyManager.get().getFrequency(7), FrequencyManager.get().getFrequency(3)));
		System.out.println("Contains : " + Frequencies.isSubSet(FrequencyManager.get().getFrequency(7), FrequencyManager.get().getFrequency(3)));
		
	}

}
