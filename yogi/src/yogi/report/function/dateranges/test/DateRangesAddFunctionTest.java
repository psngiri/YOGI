package yogi.report.function.dateranges.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.DateManager;
import yogi.period.date.range.DateRange;
import yogi.remote.CommandException;
import yogi.report.function.dateranges.DateRangesAddFunction;

public class DateRangesAddFunctionTest extends TestCase {
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
		List<DateRange> DateRanges1 = new ArrayList<DateRange>();
		DateRange DateRange1 = new DateRange(DateManager.get().getDate(2014, 1, 10), DateManager.get().getDate(2014, 1, 12));
		DateRanges1.add(DateRange1);
		
		
		List<DateRange> DateRanges2 = new ArrayList<DateRange>();
		DateRange DateRange2 = new DateRange(DateManager.get().getDate(2014, 1, 10), DateManager.get().getDate(2014, 1, 15));
		DateRanges2.add(DateRange2);
		
		DateRangesAddFunction addDateRanges = new DateRangesAddFunction();

		addDateRanges.process(DateRanges1, 0);
		addDateRanges.process(DateRanges2, 0);
		assertEquals(DateManager.get().getDate(2014, 1, 10), DateManager.get().getDate(addDateRanges.getValue().get(0).getStart().getValue()));
		assertEquals(DateManager.get().getDate(2014, 1, 12), DateManager.get().getDate(addDateRanges.getValue().get(0).getEnd().getValue()));
		assertEquals(DateManager.get().getDate(2014, 1, 13), DateManager.get().getDate(addDateRanges.getValue().get(1).getStart().getValue()));
		assertEquals(DateManager.get().getDate(2014, 1, 15), DateManager.get().getDate(addDateRanges.getValue().get(1).getEnd().getValue()));
	}
}
