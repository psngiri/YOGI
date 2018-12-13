package yogi.report.function.list.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.list.ListUnionFunction;

public class ListUnionFunctionTest extends TestCase {
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
		List<Integer> a = new ArrayList<Integer>(Arrays.asList(1,3,5));
		List<Integer> b = new ArrayList<Integer>(Arrays.asList(3,3,6,7)); // duplicates are there because of repeating segments
		
		ListUnionFunction<Integer> sumIntegerList  = new ListUnionFunction<Integer>();
		sumIntegerList.process(a, 1);
		sumIntegerList.process(b, 1);
		
		assertEquals(5, sumIntegerList.getValue().size());
		assertEquals("[1, 3, 5, 6, 7]",sumIntegerList.getValue().toString());
	}
}
