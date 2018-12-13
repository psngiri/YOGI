package yogi.report.server.config.validator.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.server.config.validator.IntegerInValidator;

public class IntegerInValidatorTest extends TestCase{
	
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
		assertTrue(new IntegerInValidator().validate("0"));
		assertTrue(new IntegerInValidator().validate("1"));
		assertTrue(new IntegerInValidator().validate("12"));
		assertTrue(new IntegerInValidator().validate("123"));
		assertTrue(new IntegerInValidator().validate("1-2"));
		assertTrue(new IntegerInValidator().validate("1-10"));
		assertTrue(new IntegerInValidator().validate("0,1,12,123"));
	}
	
}
