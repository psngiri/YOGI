package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.IntegerInCondition;

public class IntegerInConditionTest extends TestCase{
	
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

	public void testIntegerRangeInCondition() {
		IntegerInCondition condition = new IntegerInCondition("10-50,225-250,3,301");
		assertTrue(condition.satisfied(new Integer(3)));
		assertFalse(condition.satisfied(new Integer(6)));
		assertFalse(condition.satisfied(new Integer(201)));
		assertTrue(condition.satisfied(new Integer(230)));
		assertTrue(condition.satisfied(new Integer(50)));
		assertTrue(condition.satisfied(new Integer(11)));
		assertTrue(condition.satisfied(new Integer(35)));
		assertTrue(condition.satisfied(new Integer(301)));
	}
	
	public void testIntegerInCondition() {
		IntegerInCondition condition = new IntegerInCondition("3,11,35,50,230,301");
		assertTrue(condition.satisfied(new Integer(3)));
		assertFalse(condition.satisfied(new Integer(6)));
		assertFalse(condition.satisfied(new Integer(201)));
		assertTrue(condition.satisfied(new Integer(230)));
		assertTrue(condition.satisfied(new Integer(50)));
		assertTrue(condition.satisfied(new Integer(11)));
		assertTrue(condition.satisfied(new Integer(35)));
		assertTrue(condition.satisfied(new Integer(301)));
	}


}
