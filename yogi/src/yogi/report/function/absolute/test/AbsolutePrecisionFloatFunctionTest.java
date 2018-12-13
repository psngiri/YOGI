package yogi.report.function.absolute.test;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.PrecisionFloat;
import yogi.remote.CommandException;
import yogi.report.function.absolute.AbsolutePrecisionFloatFunction;

public class AbsolutePrecisionFloatFunctionTest extends TestCase {
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
		PrecisionFloat b = new PrecisionFloat(-19.13f, 1);
		PrecisionFloat c = new PrecisionFloat(-10.43f, 2);
		
		AbsolutePrecisionFloatFunction absPrecisionFloat  = new AbsolutePrecisionFloatFunction();
		
		absPrecisionFloat.process(a, 0);
		assertEquals(2.23f, absPrecisionFloat.getValue().getValue());
		assertEquals(2, absPrecisionFloat.getValue().getDecimal());
		
		absPrecisionFloat.process(b, 0);		
		assertEquals(19.13f, absPrecisionFloat.getValue().getValue());
		assertEquals(1, absPrecisionFloat.getValue().getDecimal());
		
		absPrecisionFloat.process(c, 0);		
		assertEquals(10.43f, absPrecisionFloat.getValue().getValue());
		assertEquals(2, absPrecisionFloat.getValue().getDecimal());
	}
}
