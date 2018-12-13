package yogi.report.server.config.validator.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.server.config.validator.FrequencyListValidator;

public class FrequencyListValidatorTest extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		TestErrorReporter.end();
	}

	public void test() {
		assertTrue(new FrequencyListValidator().validate(""));
		assertTrue(new FrequencyListValidator().validate("M"));
		assertTrue(new FrequencyListValidator().validate("T"));
		assertTrue(new FrequencyListValidator().validate("MTW"));
		assertTrue(new FrequencyListValidator().validate("FJS"));
		assertTrue(new FrequencyListValidator().validate("MTWQFJS"));
		assertTrue(new FrequencyListValidator().validate(",M,T,MTW,FJS,MTWQFJS"));
	}
	
}
