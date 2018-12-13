package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.LongInCondition;

import junit.framework.TestCase;

public class LongInConditionTest extends TestCase{
	
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

	public void testLongRangeInCondition() {
		LongInCondition condition = new LongInCondition("10-50,225-250,3,301");
		assertTrue(condition.satisfied(new Long(3)));
		assertFalse(condition.satisfied(new Long(6)));
		assertFalse(condition.satisfied(new Long(201)));
		assertTrue(condition.satisfied(new Long(230)));
		assertTrue(condition.satisfied(new Long(50)));
		assertTrue(condition.satisfied(new Long(11)));
		assertTrue(condition.satisfied(new Long(35)));
		assertTrue(condition.satisfied(new Long(301)));
	}
	
	public void testLongInCondition() {
		LongInCondition condition = new LongInCondition("3,11,35,50,230,301");
		assertTrue(condition.satisfied(new Long(3)));
		assertFalse(condition.satisfied(new Long(6)));
		assertFalse(condition.satisfied(new Long(201)));
		assertTrue(condition.satisfied(new Long(230)));
		assertTrue(condition.satisfied(new Long(50)));
		assertTrue(condition.satisfied(new Long(11)));
		assertTrue(condition.satisfied(new Long(35)));
		assertTrue(condition.satisfied(new Long(301)));
	}


}
