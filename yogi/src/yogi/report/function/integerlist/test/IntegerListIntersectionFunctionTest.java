package yogi.report.function.integerlist.test;
//package yogi.report.function.sum.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.integerlist.IntegerListIntersectionFunction;

public class IntegerListIntersectionFunctionTest extends TestCase {
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
		List<Integer> a = new ArrayList<Integer>();
		List<Integer> b = new ArrayList<Integer>();
		
		a.add(3);
		a.add(5);
		a.add(9);
		b.add(5);
		b.add(7);
		b.add(9);
		
		IntegerListIntersectionFunction sumIntegerList  = new IntegerListIntersectionFunction();
		sumIntegerList.process(a, 1);
		sumIntegerList.process(b, 1);
		
		assertEquals(2, sumIntegerList.getValue().size());
		assertEquals(5, sumIntegerList.getValue().get(0).intValue());
		assertEquals(9, sumIntegerList.getValue().get(1).intValue());
	}
}
