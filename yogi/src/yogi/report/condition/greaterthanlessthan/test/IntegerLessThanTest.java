package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.IntegerLessThanCondition;
import yogi.report.condition.IntegerLessThanOrEqualsCondition;

import junit.framework.TestCase;

public class IntegerLessThanTest extends TestCase{
	
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

	public void testIntegerLessThanCondition() {
		IntegerLessThanCondition condition = new IntegerLessThanCondition("10");
		IntegerLessThanCondition condition2 = new IntegerLessThanCondition("0");
		IntegerLessThanCondition condition3 = new IntegerLessThanCondition("100");
		assertTrue(condition.satisfied(new Integer(3)));
		assertTrue(condition.satisfied(new Integer(6)));
		assertFalse(condition.satisfied(new Integer(201)));
		assertFalse(condition.satisfied(new Integer(230)));
		assertFalse(condition.satisfied(new Integer(50)));
		assertFalse(condition.satisfied(new Integer(11)));
		assertFalse(condition.satisfied(new Integer(35)));
		assertTrue(condition.satisfied(new Integer(0)));
		
		assertFalse(condition2.satisfied(new Integer(3)));
		assertFalse(condition2.satisfied(new Integer(6)));
		assertFalse(condition2.satisfied(new Integer(201)));
		assertFalse(condition2.satisfied(new Integer(230)));
		assertFalse(condition2.satisfied(new Integer(50)));
		assertFalse(condition2.satisfied(new Integer(11)));
		assertTrue(condition2.satisfied(new Integer(-1)));
		assertFalse(condition2.satisfied(new Integer(0)));
	
		assertTrue(condition3.satisfied(new Integer(3)));
		assertTrue(condition3.satisfied(new Integer(6)));
		assertFalse(condition3.satisfied(new Integer(201)));
		assertFalse(condition3.satisfied(new Integer(230)));
		assertTrue(condition3.satisfied(new Integer(50)));
		assertTrue(condition3.satisfied(new Integer(0)));
	}
	
	public void testIntegerLessThanOrEqualsCondition() {
		IntegerLessThanOrEqualsCondition condition = new IntegerLessThanOrEqualsCondition("10");
		IntegerLessThanOrEqualsCondition condition2 = new IntegerLessThanOrEqualsCondition("0");
		IntegerLessThanOrEqualsCondition condition3 = new IntegerLessThanOrEqualsCondition("100");
		assertTrue(condition.satisfied(new Integer(3)));
		assertTrue(condition.satisfied(new Integer(6)));
		assertFalse(condition.satisfied(new Integer(201)));
		assertFalse(condition.satisfied(new Integer(230)));
		assertFalse(condition.satisfied(new Integer(50)));
		assertFalse(condition.satisfied(new Integer(11)));
		assertFalse(condition.satisfied(new Integer(35)));
		assertTrue(condition.satisfied(new Integer(0)));
		
		assertFalse(condition2.satisfied(new Integer(3)));
		assertFalse(condition2.satisfied(new Integer(6)));
		assertFalse(condition2.satisfied(new Integer(201)));
		assertFalse(condition2.satisfied(new Integer(230)));
		assertFalse(condition2.satisfied(new Integer(50)));
		assertFalse(condition2.satisfied(new Integer(11)));
		assertTrue(condition2.satisfied(new Integer(-1)));
		assertTrue(condition2.satisfied(new Integer(0)));
	
		assertTrue(condition3.satisfied(new Integer(3)));
		assertTrue(condition3.satisfied(new Integer(6)));
		assertFalse(condition3.satisfied(new Integer(201)));
		assertFalse(condition3.satisfied(new Integer(230)));
		assertTrue(condition3.satisfied(new Integer(50)));
		assertTrue(condition3.satisfied(new Integer(0)));;
	}
}
