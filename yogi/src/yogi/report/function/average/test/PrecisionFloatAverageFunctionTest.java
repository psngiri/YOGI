package yogi.report.function.average.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.PrecisionFloat;
import yogi.remote.CommandException;
import yogi.report.function.average.PrecisionFloatAverageFunction;

public class PrecisionFloatAverageFunctionTest extends TestCase {
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
		PrecisionFloat a = new PrecisionFloat(2.23f, 2);
		PrecisionFloat b = new PrecisionFloat(19.14f, 2);
		PrecisionFloat c = new PrecisionFloat(10.44f, 2);
		
		PrecisionFloatAverageFunction avgPrecisionFloat  = new PrecisionFloatAverageFunction();
		
		avgPrecisionFloat.process(a, 1);
		avgPrecisionFloat.process(b, 1);
		avgPrecisionFloat.process(c, 1);
		
		assertEquals(10.6033325f, avgPrecisionFloat.getValue().getValue());
		assertEquals(2, avgPrecisionFloat.getValue().getDecimal());
	}
}
