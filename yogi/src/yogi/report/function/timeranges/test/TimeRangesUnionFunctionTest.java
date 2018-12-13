package yogi.report.function.timeranges.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.time.TimeManager;
import yogi.period.time.range.TimeRange;
import yogi.remote.CommandException;
import yogi.report.function.timeranges.TimeRangesUnionFunction;

public class TimeRangesUnionFunctionTest extends TestCase {
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
		TimeRange timeRange1 = new TimeRange(TimeManager.get().getTime("800"), TimeManager.get().getTime("900"));
		timeRanges1.add(timeRange1);
		
		
		List<TimeRange> timeRanges2 = new ArrayList<TimeRange>();
		TimeRange timeRange2 = new TimeRange(TimeManager.get().getTime("800"), TimeManager.get().getTime("1000"));
		timeRanges2.add(timeRange2);
		
		List<TimeRange> timeRanges3 = new ArrayList<TimeRange>();
		TimeRange timeRange3 = new TimeRange(TimeManager.get().getTime("800"), TimeManager.get().getTime("1115"));
		timeRanges3.add(timeRange3);
		
		TimeRangesUnionFunction unionTimeRanges = new TimeRangesUnionFunction();
		
		unionTimeRanges.process(timeRanges1, 0);
		unionTimeRanges.process(timeRanges2, 0);
		unionTimeRanges.process(timeRanges3, 0);
		assertEquals(3,unionTimeRanges.getValue().size());
		assertEquals(TimeManager.get().getTime("800"), unionTimeRanges.getValue().get(0).getStartTime());
		assertEquals(TimeManager.get().getTime("1115"), unionTimeRanges.getValue().get(unionTimeRanges.getValue().size()-1).getEndTime());
	}
}
