package yogi.report.function.max.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.PrecisionFloat;
import yogi.remote.CommandException;
import yogi.report.function.max.MaxPrecisionFloatFunction;

public class MaxPrecisionFloatFunctionTest extends TestCase {
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
		PrecisionFloat a = new PrecisionFloat(7.23f, 0);
		PrecisionFloat b = new PrecisionFloat(7.13f, 2);
		PrecisionFloat c = new PrecisionFloat(7.43f, 1);
		
		MaxPrecisionFloatFunction maxPrecisionFloat  = new MaxPrecisionFloatFunction();
		maxPrecisionFloat.process(a, 0);
		maxPrecisionFloat.process(b, 0);
		maxPrecisionFloat.process(c, 0);
		
		assertEquals(7.43f, maxPrecisionFloat.getValue().getValue());
		assertEquals(1, maxPrecisionFloat.getValue().getDecimal());		
	}
}
