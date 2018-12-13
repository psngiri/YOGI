package yogi.report.server.config.validator.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.server.config.validator.DateRangesListInValidator;

public class DateRangesListInValidatorTest extends TestCase{
	
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
		assertTrue(new DateRangesListInValidator().validate("01JAN01-31JAN01"));
		assertTrue(new DateRangesListInValidator().validate("01JAN01-31JAN01^01MAR01-14MAR01"));
		assertTrue(new DateRangesListInValidator().validate("01JAN01-31JAN01^01MAR01-14MAR01^01JUN01-31JUN01"));
		assertTrue(new DateRangesListInValidator().validate("01JAN01-31JAN01,01JAN01-31JAN01^01MAR01-14MAR01,01JAN01-31JAN01^01MAR01-14MAR01^01JUN01-31JUN01"));
	}
	
}
