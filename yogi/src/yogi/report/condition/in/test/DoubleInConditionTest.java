package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.DoubleInCondition;

import junit.framework.TestCase;

public class DoubleInConditionTest extends TestCase{
	
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

	public void testDoubleRangeInCondition() {
		DoubleInCondition condition = new DoubleInCondition("10-50,225-250,3,301");
		assertTrue(condition.satisfied(new Double(3)));
		assertFalse(condition.satisfied(new Double(6)));
		assertFalse(condition.satisfied(new Double(201)));
		assertTrue(condition.satisfied(new Double(230)));
		assertTrue(condition.satisfied(new Double(50)));
		assertTrue(condition.satisfied(new Double(11)));
		assertTrue(condition.satisfied(new Double(35)));
		assertTrue(condition.satisfied(new Double(301)));
	}
	
	public void testDoubleInCondition() {
		DoubleInCondition condition = new DoubleInCondition("3,11,35,50,230,301");
		assertTrue(condition.satisfied(new Double(3)));
		assertFalse(condition.satisfied(new Double(6)));
		assertFalse(condition.satisfied(new Double(201)));
		assertTrue(condition.satisfied(new Double(230)));
		assertTrue(condition.satisfied(new Double(50)));
		assertTrue(condition.satisfied(new Double(11)));
		assertTrue(condition.satisfied(new Double(35)));
		assertTrue(condition.satisfied(new Double(301)));
	}


}
