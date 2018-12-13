package yogi.period.time.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.period.time.Time;
import yogi.period.time.TimeComparator;
import yogi.period.time.TimeManager;
import yogi.period.time.TimeUtils;

public class TimeComparatorTest extends TestCase {

	public void test()
	{
		List<Time> times = new ArrayList<Time>();
		times.add(TimeManager.get().getTime(10));
		times.add(TimeManager.get().getTime(1400));
		times.add(TimeManager.get().getTime(20));
		times.add(TimeManager.get().getTime(1440));
		times.add(TimeManager.get().getTime(30));
		times.add(TimeManager.get().getTime(1420));
		TimeUtils.sort(times, new MyTimeComparator());
		assertEquals(1400, times.get(0).getTime());
		assertEquals(1420, times.get(1).getTime());
		assertEquals(1440, times.get(2).getTime());
		assertEquals(10, times.get(3).getTime());
		assertEquals(20, times.get(4).getTime());
		assertEquals(30, times.get(5).getTime());
	}
	
	public void test1()
	{
		List<Time> times = new ArrayList<Time>();
		times.add(TimeManager.get().getTime(730));
		times.add(TimeManager.get().getTime(680));
		times.add(TimeManager.get().getTime(740));
		times.add(TimeManager.get().getTime(720));
		times.add(TimeManager.get().getTime(750));
		times.add(TimeManager.get().getTime(700));
		TimeUtils.sort(times, new MyTimeComparator());
		assertEquals(680, times.get(0).getTime());
		assertEquals(700, times.get(1).getTime());
		assertEquals(720, times.get(2).getTime());
		assertEquals(730, times.get(3).getTime());
		assertEquals(740, times.get(4).getTime());
		assertEquals(750, times.get(5).getTime());
	}
	
	static class MyTimeComparator extends TimeComparator<Time>
	{

		@Override
		protected Time getTime(Time object) {
			return object;
		}
		
	}
}
