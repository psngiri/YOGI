package yogi.period.time.test;

import yogi.period.time.Time;
import yogi.period.time.TimeManager;
import yogi.period.time.Times;
import yogi.period.time.io.HHMMTimeScanner;

import junit.framework.TestCase;

public class TimeTest extends TestCase {

	public void testTime()
	{
		Time time1 = TimeManager.get().getTime(100);
		Time time2 = TimeManager.get().getTime(100);
		assertEquals(true, time1 == time2);
	}
	
	public void testSubtract(){
		Time time1 = TimeManager.get().getTime(180);
		Time time2 = TimeManager.get().getTime(300);
		assertEquals(120, Times.subtract(time2, time1).getTime());
		
		Time time3 = TimeManager.get().getTime(60);
		assertEquals(1320, Times.subtract(time3, time1).getTime());
		
		time3 = TimeManager.get().getTime(150);
		assertEquals(1410, Times.subtract(time3, time1).getTime());
	}
	
	public void testHHMMTimeScanner() {
		HHMMTimeScanner scanner = new HHMMTimeScanner();
		
		Time time1 = scanner.scan("1930").create();
		assertEquals(1170, time1.getTime());

		String gmtOffset = new String("-0700");
		Time time2 = scanner.scan(gmtOffset.substring(1)).create();
		short gmtOffsetInMin = time2.getTime();
		assertEquals(420, gmtOffsetInMin);
		if (gmtOffset.substring(0,1).equals("-")) gmtOffsetInMin *= -1; 
		assertEquals(-420, gmtOffsetInMin);
		assertEquals(48, scanner.scan("48").create().getTime());
}
	
}
