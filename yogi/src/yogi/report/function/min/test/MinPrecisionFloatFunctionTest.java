package yogi.report.function.min.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.PrecisionFloat;
import yogi.remote.CommandException;
import yogi.report.function.min.MinPrecisionFloatFunction;

public class MinPrecisionFloatFunctionTest extends TestCase {
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
		PrecisionFloat a = new PrecisionFloat(7.23f, 1);
		PrecisionFloat b = new PrecisionFloat(7.13f, 2);
		PrecisionFloat c = new PrecisionFloat(7.43f, 0);
		
		MinPrecisionFloatFunction minPrecisionFloat  = new MinPrecisionFloatFunction();
		minPrecisionFloat.process(a, 0);
		minPrecisionFloat.process(b, 0);
		minPrecisionFloat.process(c, 0);
		
		assertEquals(7.13f, minPrecisionFloat.getValue().getValue());
		assertEquals(2, minPrecisionFloat.getValue().getDecimal());		
	}
}
