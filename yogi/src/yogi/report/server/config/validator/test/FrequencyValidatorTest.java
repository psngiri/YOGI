package yogi.report.server.config.validator.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.server.config.validator.FrequencyValidator;

public class FrequencyValidatorTest extends TestCase{
	
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
		assertTrue(new FrequencyValidator().validate(""));
		assertTrue(new FrequencyValidator().validate("M"));
		assertTrue(new FrequencyValidator().validate("T"));
		assertTrue(new FrequencyValidator().validate("MTW"));
		assertTrue(new FrequencyValidator().validate("FJS"));
		assertTrue(new FrequencyValidator().validate("MTWQFJS"));
	}
	
}
