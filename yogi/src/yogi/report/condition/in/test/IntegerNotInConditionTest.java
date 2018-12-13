package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.IntegerNotInCondition;

public class IntegerNotInConditionTest extends TestCase{
	
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

	public void testIntegerNotInCondition() {
		IntegerNotInCondition condition = new IntegerNotInCondition("10-50,225-250,3,301");
		assertFalse(condition.satisfied(new Integer(3)));
		assertTrue(condition.satisfied(new Integer(6)));
		assertTrue(condition.satisfied(new Integer(201)));
		assertFalse(condition.satisfied(new Integer(50)));
		assertFalse(condition.satisfied(new Integer(11)));
		assertFalse(condition.satisfied(new Integer(35)));
		assertFalse(condition.satisfied(new Integer(230)));
		assertFalse(condition.satisfied(new Integer(301)));
	}


}
