package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.LongLessThanCondition;
import yogi.report.condition.LongLessThanOrEqualsCondition;

import junit.framework.TestCase;

public class LongLessThanTest extends TestCase{
	
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

	public void testLongLessThanCondition() {
		LongLessThanCondition condition = new LongLessThanCondition("10");
		LongLessThanCondition condition2 = new LongLessThanCondition("0");
		LongLessThanCondition condition3 = new LongLessThanCondition("100");
		assertTrue(condition.satisfied(new Long(3)));
		assertTrue(condition.satisfied(new Long(6)));
		assertFalse(condition.satisfied(new Long(201)));
		assertFalse(condition.satisfied(new Long(230)));
		assertFalse(condition.satisfied(new Long(50)));
		assertFalse(condition.satisfied(new Long(11)));
		assertFalse(condition.satisfied(new Long(35)));
		assertTrue(condition.satisfied(new Long(0)));
		
		assertFalse(condition2.satisfied(new Long(3)));
		assertFalse(condition2.satisfied(new Long(6)));
		assertFalse(condition2.satisfied(new Long(201)));
		assertFalse(condition2.satisfied(new Long(230)));
		assertFalse(condition2.satisfied(new Long(50)));
		assertFalse(condition2.satisfied(new Long(11)));
		assertTrue(condition2.satisfied(new Long(-1)));
		assertFalse(condition2.satisfied(new Long(0)));
	
		assertTrue(condition3.satisfied(new Long(3)));
		assertTrue(condition3.satisfied(new Long(6)));
		assertFalse(condition3.satisfied(new Long(201)));
		assertFalse(condition3.satisfied(new Long(230)));
		assertTrue(condition3.satisfied(new Long(50)));
		assertTrue(condition3.satisfied(new Long(0)));
	}
	
	public void testLongLessThanOrEqualsCondition() {
		LongLessThanOrEqualsCondition condition = new LongLessThanOrEqualsCondition("10");
		LongLessThanOrEqualsCondition condition2 = new LongLessThanOrEqualsCondition("0");
		LongLessThanOrEqualsCondition condition3 = new LongLessThanOrEqualsCondition("100");
		assertTrue(condition.satisfied(new Long(3)));
		assertTrue(condition.satisfied(new Long(6)));
		assertFalse(condition.satisfied(new Long(201)));
		assertFalse(condition.satisfied(new Long(230)));
		assertFalse(condition.satisfied(new Long(50)));
		assertFalse(condition.satisfied(new Long(11)));
		assertFalse(condition.satisfied(new Long(35)));
		assertTrue(condition.satisfied(new Long(0)));
		
		assertFalse(condition2.satisfied(new Long(3)));
		assertFalse(condition2.satisfied(new Long(6)));
		assertFalse(condition2.satisfied(new Long(201)));
		assertFalse(condition2.satisfied(new Long(230)));
		assertFalse(condition2.satisfied(new Long(50)));
		assertFalse(condition2.satisfied(new Long(11)));
		assertTrue(condition2.satisfied(new Long(-1)));
		assertTrue(condition2.satisfied(new Long(0)));
	
		assertTrue(condition3.satisfied(new Long(3)));
		assertTrue(condition3.satisfied(new Long(6)));
		assertFalse(condition3.satisfied(new Long(201)));
		assertFalse(condition3.satisfied(new Long(230)));
		assertTrue(condition3.satisfied(new Long(50)));
		assertTrue(condition3.satisfied(new Long(0)));;
	}
}
