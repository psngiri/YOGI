package yogi.report.function.timeranges.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.time.TimeManager;
import yogi.period.time.range.TimeRange;
import yogi.remote.CommandException;
import yogi.report.function.timeranges.TimeRangesAddFunction;

public class TimeRangesAddFunctionTest extends TestCase {
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception
	{
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}
		
	public void test() throws CommandException {
		List<TimeRange> timeRanges1 = new ArrayList<TimeRange>();
		TimeRange timeRange1 = new TimeRange(TimeManager.get().getTime("930"), TimeManager.get().getTime("1000"));
		timeRanges1.add(timeRange1);
		
		
		List<TimeRange> timeRanges2 = new ArrayList<TimeRange>();
		TimeRange timeRange2 = new TimeRange(TimeManager.get().getTime("930"), TimeManager.get().getTime("1100"));
		timeRanges2.add(timeRange2);
		
		TimeRangesAddFunction addTimeRanges = new TimeRangesAddFunction();

		addTimeRanges.process(timeRanges1, 0);
		addTimeRanges.process(timeRanges2, 0);
		assertEquals(TimeManager.get().getTime("930"), addTimeRanges.getValue().get(0).getStartTime());
		assertEquals(TimeManager.get().getTime("1000"), addTimeRanges.getValue().get(0).getEndTime());
		assertEquals(TimeManager.get().getTime("1000"), addTimeRanges.getValue().get(1).getStartTime());
		assertEquals(TimeManager.get().getTime("1100"), addTimeRanges.getValue().get(1).getEndTime());
	}
}
