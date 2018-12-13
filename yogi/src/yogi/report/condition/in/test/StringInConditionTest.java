package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.StringInCondition;

import junit.framework.TestCase;

public class StringInConditionTest extends TestCase{
	
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
		StringInCondition condition = new StringInCondition("3.6,6,11.3");
		assertTrue(condition.satisfied("3.6"));
		assertTrue(condition.satisfied("6"));
		assertTrue(condition.satisfied("11.3"));
		assertFalse(condition.satisfied("11.34"));
	}

}
