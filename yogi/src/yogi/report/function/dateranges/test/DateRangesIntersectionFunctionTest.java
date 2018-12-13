package yogi.report.function.dateranges.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.DateManager;
import yogi.period.date.range.DateRange;
import yogi.remote.CommandException;
import yogi.report.function.dateranges.DateRangesIntersectionFunction;

public class DateRangesIntersectionFunctionTest extends TestCase {
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
		List<DateRange> dateRanges1 = new ArrayList<DateRange>();
		DateRange dateRange1 = new DateRange(DateManager.get().getDate(2014, 2, 20), DateManager.get().getDate(2014, 2, 25));
		dateRanges1.add(dateRange1);
		
		
		List<DateRange> dateRanges2 = new ArrayList<DateRange>();
		DateRange dateRange2 = new DateRange(DateManager.get().getDate(2014, 2, 22), DateManager.get().getDate(2014, 2, 28));
		dateRanges2.add(dateRange2);
		
		DateRangesIntersectionFunction intersectDateRanges = new DateRangesIntersectionFunction();

		intersectDateRanges.process(dateRanges1, 0);
		intersectDateRanges.process(dateRanges2, 0);
		assertEquals(1,intersectDateRanges.getValue().size());
		assertEquals(DateManager.get().getDate(2014, 2, 22), DateManager.get().getDate(intersectDateRanges.getValue().get(0).getStart().getValue()));
		assertEquals(DateManager.get().getDate(2014, 2, 25), DateManager.get().getDate(intersectDateRanges.getValue().get(0).getEnd().getValue()));
	}
}
