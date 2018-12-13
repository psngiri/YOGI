package yogi.period.time.test;

import junit.framework.TestCase;

import yogi.period.time.TimeManager;
import yogi.period.time.TimeUtils;

public class TimeUtilsTest extends TestCase {

	public void test()
	{
		assertEquals(-20, TimeUtils.getDistance(TimeManager.get().getTime(30), TimeManager.get().getTime(10)));
		assertEquals(20, TimeUtils.getDistance(TimeManager.get().getTime(10), TimeManager.get().getTime(30)));
		assertEquals(-20, TimeUtils.getDistance(TimeManager.get().getTime(10), TimeManager.get().getTime(1430)));
		assertEquals(20, TimeUtils.getDistance(TimeManager.get().getTime(1430), TimeManager.get().getTime(10)));
		assertEquals(-720, TimeUtils.getDistance(TimeManager.get().getTime(730), TimeManager.get().getTime(10)));
		assertEquals(720, TimeUtils.getDistance(TimeManager.get().getTime(10), TimeManager.get().getTime(730)));
		assertEquals(719, TimeUtils.getDistance(TimeManager.get().getTime(731), TimeManager.get().getTime(10)));
		assertEquals(-719, TimeUtils.getDistance(TimeManager.get().getTime(10), TimeManager.get().getTime(731)));
		assertEquals(-50, TimeUtils.getDistance(TimeManager.get().getTime(10), TimeManager.get().getTime(1400)));
	}
	
	public void testGetTimeInMinutes()
	{
		String time = " .50";
		int timeInMinutes = TimeUtils.getTimeInMinutes(time, '.');
		assertEquals(50, timeInMinutes);
		time = "10.50";
		timeInMinutes = TimeUtils.getTimeInMinutes(time, '.');
		assertEquals(650, timeInMinutes);
		time = "50";
		timeInMinutes = TimeUtils.getTimeInMinutes(time, '.');
		assertEquals(50, timeInMinutes);
		time = "150";
		timeInMinutes = TimeUtils.getTimeInMinutes(time, '.');
		assertEquals(150, timeInMinutes);
	}
}
