package yogi.report.function.max.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.max.MaxIntegerFunction;

public class MaxIntegerFunctionTest extends TestCase {
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
		Integer a = 5;
		Integer b = 15;
		Integer c = 8;
		
		MaxIntegerFunction maxInteger  = new MaxIntegerFunction();
		maxInteger.process(a, 0);
		maxInteger.process(b, 0);
		maxInteger.process(c, 0);
		
		assertEquals(15, maxInteger.getValue().intValue());
	}
}
