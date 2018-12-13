package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.LongGreaterThanCondition;
import yogi.report.condition.LongGreaterThanOrEqualsCondition;

import junit.framework.TestCase;

public class LongGreaterThanTest extends TestCase{
	
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

	public void testLongGreaterThanCondition() {
		LongGreaterThanCondition condition = new LongGreaterThanCondition("10");
		LongGreaterThanCondition condition2 = new LongGreaterThanCondition("0");
		LongGreaterThanCondition condition3 = new LongGreaterThanCondition("100");
		assertFalse(condition.satisfied(new Long(3)));
		assertFalse(condition.satisfied(new Long(6)));
		assertTrue(condition.satisfied(new Long(201)));
		assertTrue(condition.satisfied(new Long(230)));
		assertTrue(condition.satisfied(new Long(50)));
		assertTrue(condition.satisfied(new Long(11)));
		assertTrue(condition.satisfied(new Long(35)));
		assertFalse(condition.satisfied(new Long(0)));
		
		assertTrue(condition2.satisfied(new Long(3)));
		assertTrue(condition2.satisfied(new Long(6)));
		assertTrue(condition2.satisfied(new Long(201)));
		assertTrue(condition2.satisfied(new Long(230)));
		assertTrue(condition2.satisfied(new Long(50)));
		assertTrue(condition2.satisfied(new Long(11)));
		assertFalse(condition2.satisfied(new Long(-1)));
		assertFalse(condition2.satisfied(new Long(0)));
		
		assertFalse(condition3.satisfied(new Long(3)));
		assertFalse(condition3.satisfied(new Long(6)));
		assertTrue(condition3.satisfied(new Long(201)));
		assertTrue(condition3.satisfied(new Long(230)));
		assertFalse(condition3.satisfied(new Long(50)));
		assertFalse(condition3.satisfied(new Long(0)));
	}
	
	public void testLongGreaterThanOrEqualsCondition() {
		LongGreaterThanOrEqualsCondition condition = new LongGreaterThanOrEqualsCondition("10");
		LongGreaterThanOrEqualsCondition condition2 = new LongGreaterThanOrEqualsCondition("0");
		LongGreaterThanOrEqualsCondition condition3 = new LongGreaterThanOrEqualsCondition("100");
		assertFalse(condition.satisfied(new Long(3)));
		assertFalse(condition.satisfied(new Long(6)));
		assertTrue(condition.satisfied(new Long(201)));
		assertTrue(condition.satisfied(new Long(230)));
		assertTrue(condition.satisfied(new Long(50)));
		assertTrue(condition.satisfied(new Long(10)));
		assertTrue(condition.satisfied(new Long(35)));
		assertFalse(condition.satisfied(new Long(0)));
		
		assertTrue(condition2.satisfied(new Long(3)));
		assertTrue(condition2.satisfied(new Long(6)));
		assertTrue(condition2.satisfied(new Long(201)));
		assertTrue(condition2.satisfied(new Long(230)));
		assertTrue(condition2.satisfied(new Long(50)));
		assertTrue(condition2.satisfied(new Long(11)));
		assertFalse(condition2.satisfied(new Long(-1)));
		assertTrue(condition2.satisfied(new Long(0)));
		
		assertFalse(condition3.satisfied(new Long(3)));
		assertFalse(condition3.satisfied(new Long(6)));
		assertTrue(condition3.satisfied(new Long(201)));
		assertTrue(condition3.satisfied(new Long(230)));
		assertTrue(condition3.satisfied(new Long(100)));
		assertFalse(condition3.satisfied(new Long(0)));
	}
}
