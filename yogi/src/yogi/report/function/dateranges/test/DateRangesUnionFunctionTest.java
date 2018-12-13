package yogi.report.function.dateranges.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.DateManager;
import yogi.period.date.range.DateRange;
import yogi.remote.CommandException;
import yogi.report.function.dateranges.DateRangesUnionFunction;

public class DateRangesUnionFunctionTest extends TestCase {
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
		DateRange dateRange1 = new DateRange(DateManager.get().getDate(2014, 3, 10), DateManager.get().getDate(2014, 3, 15));
		dateRanges1.add(dateRange1);
		
		
		List<DateRange> dateRanges2 = new ArrayList<DateRange>();
		DateRange dateRange2 = new DateRange(DateManager.get().getDate(2014, 3, 10), DateManager.get().getDate(2014, 3, 25));
		dateRanges2.add(dateRange2);
		
		List<DateRange> dateRanges3 = new ArrayList<DateRange>();
		DateRange dateRange3 = new DateRange(DateManager.get().getDate(2014, 3, 10), DateManager.get().getDate(2014, 3, 30));
		dateRanges3.add(dateRange3);
		
		DateRangesUnionFunction unionDateRanges = new DateRangesUnionFunction();
		
		unionDateRanges.process(dateRanges1, 0);
		unionDateRanges.process(dateRanges2, 0);
		unionDateRanges.process(dateRanges3, 0);
		List<DateRange> rtnValue = unionDateRanges.getValue();
		assertEquals(1, rtnValue.size());
		assertEquals(DateManager.get().getDate(2014, 3, 10), DateManager.get().getDate(unionDateRanges.getValue().get(0).getStart().getValue()));
		assertEquals(DateManager.get().getDate(2014, 3, 30), DateManager.get().getDate(unionDateRanges.getValue().get(unionDateRanges.getValue().size()-1).getEnd().getValue()));
	}
	
	public void testBreakInDates() throws CommandException {
		List<DateRange> dateRanges1 = new ArrayList<DateRange>();
		DateRange dateRange1 = new DateRange(DateManager.get().getDate(2014, 3, 10), DateManager.get().getDate(2014, 3, 11));
		dateRanges1.add(dateRange1);
		
		
		List<DateRange> dateRanges2 = new ArrayList<DateRange>();
		DateRange dateRange2 = new DateRange(DateManager.get().getDate(2014, 3, 12), DateManager.get().getDate(2014, 3, 25));
		dateRanges2.add(dateRange2);
		
		List<DateRange> dateRanges3 = new ArrayList<DateRange>();
		DateRange dateRange3 = new DateRange(DateManager.get().getDate(2014, 3, 28), DateManager.get().getDate(2014, 3, 30));
		dateRanges3.add(dateRange3);
		
		DateRangesUnionFunction unionDateRanges = new DateRangesUnionFunction();
		
		unionDateRanges.process(dateRanges1, 0);
		unionDateRanges.process(dateRanges2, 0);
		unionDateRanges.process(dateRanges3, 0);
		List<DateRange> rtnValue = unionDateRanges.getValue();
		assertEquals(2, rtnValue.size());
		assertEquals(DateManager.get().getDate(2014, 3, 25), DateManager.get().getDate(unionDateRanges.getValue().get(0).getEnd().getValue()));
		assertEquals(DateManager.get().getDate(2014, 3, 28), DateManager.get().getDate(unionDateRanges.getValue().get(unionDateRanges.getValue().size()-1).getStart().getValue()));
	}
}
