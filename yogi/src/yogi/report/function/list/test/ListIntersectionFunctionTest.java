package yogi.report.function.list.test;
//package yogi.report.function.sum.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.list.ListIntersectionFunction;

public class ListIntersectionFunctionTest extends TestCase {
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
		List<Integer> a = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,7)); // duplicates are there because of repeating segments
		List<Integer> b = new ArrayList<Integer>(Arrays.asList(9,3,7,1,5));
		List<Integer> c = new ArrayList<Integer>(Arrays.asList(3,7,123));
		
		ListIntersectionFunction<Integer> sumIntegerList  = new ListIntersectionFunction<Integer>();
		sumIntegerList.process(a, 1);
		sumIntegerList.process(b, 1);
		sumIntegerList.process(c, 1);
		
		assertEquals(3, sumIntegerList.getValue().size());
		assertEquals("[3, 7, 7]", sumIntegerList.getValue().toString());
	}
}
