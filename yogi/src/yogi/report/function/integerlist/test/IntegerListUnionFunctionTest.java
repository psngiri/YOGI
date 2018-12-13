package yogi.report.function.integerlist.test;
//package yogi.report.function.sum.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.integerlist.IntegerListUnionFunction;

public class IntegerListUnionFunctionTest extends TestCase {
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
		b.add(6);
		b.add(7);
		
		IntegerListUnionFunction sumIntegerList  = new IntegerListUnionFunction();
		sumIntegerList.process(a, 1);
		sumIntegerList.process(b, 1);
		
		assertEquals(4, sumIntegerList.getValue().size());
		assertEquals(3, sumIntegerList.getValue().get(0).intValue());
		assertEquals(5, sumIntegerList.getValue().get(1).intValue());
		assertEquals(6, sumIntegerList.getValue().get(2).intValue());
		assertEquals(7, sumIntegerList.getValue().get(3).intValue());
	}
}
