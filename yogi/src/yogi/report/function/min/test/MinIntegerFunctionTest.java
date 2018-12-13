package yogi.report.function.min.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.CommandException;
import yogi.report.function.min.MinIntegerFunction;

public class MinIntegerFunctionTest extends TestCase {
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
		Integer a = 8;
		Integer b = 32;
		Integer c = 4;
		
		MinIntegerFunction minInteger  = new MinIntegerFunction();
		minInteger.process(a, 0);
		minInteger.process(b, 0);
		minInteger.process(c, 0);
		
		assertEquals(4, minInteger.getValue().intValue());
	}
}
