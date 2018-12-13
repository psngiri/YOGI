package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.IntegerGreaterThanCondition;
import yogi.report.condition.IntegerGreaterThanOrEqualsCondition;

import junit.framework.TestCase;

public class IntegerGreaterThanTest extends TestCase{
	
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

	public void testIntegerGreaterThanCondition() {
		IntegerGreaterThanCondition condition = new IntegerGreaterThanCondition("10");
		IntegerGreaterThanCondition condition2 = new IntegerGreaterThanCondition("0");
		IntegerGreaterThanCondition condition3 = new IntegerGreaterThanCondition("100");
		assertFalse(condition.satisfied(new Integer(3)));
		assertFalse(condition.satisfied(new Integer(6)));
		assertTrue(condition.satisfied(new Integer(201)));
		assertTrue(condition.satisfied(new Integer(230)));
		assertTrue(condition.satisfied(new Integer(50)));
		assertTrue(condition.satisfied(new Integer(11)));
		assertTrue(condition.satisfied(new Integer(35)));
		assertFalse(condition.satisfied(new Integer(0)));
		
		assertTrue(condition2.satisfied(new Integer(3)));
		assertTrue(condition2.satisfied(new Integer(6)));
		assertTrue(condition2.satisfied(new Integer(201)));
		assertTrue(condition2.satisfied(new Integer(230)));
		assertTrue(condition2.satisfied(new Integer(50)));
		assertTrue(condition2.satisfied(new Integer(11)));
		assertFalse(condition2.satisfied(new Integer(-1)));
		assertFalse(condition2.satisfied(new Integer(0)));
		
		assertFalse(condition3.satisfied(new Integer(3)));
		assertFalse(condition3.satisfied(new Integer(6)));
		assertTrue(condition3.satisfied(new Integer(201)));
		assertTrue(condition3.satisfied(new Integer(230)));
		assertFalse(condition3.satisfied(new Integer(50)));
		assertFalse(condition3.satisfied(new Integer(0)));
	}
	
	public void testIntegerGreaterThanOrEqualsCondition() {
		IntegerGreaterThanOrEqualsCondition condition = new IntegerGreaterThanOrEqualsCondition("10");
		IntegerGreaterThanOrEqualsCondition condition2 = new IntegerGreaterThanOrEqualsCondition("0");
		IntegerGreaterThanOrEqualsCondition condition3 = new IntegerGreaterThanOrEqualsCondition("100");
		assertFalse(condition.satisfied(new Integer(3)));
		assertFalse(condition.satisfied(new Integer(6)));
		assertTrue(condition.satisfied(new Integer(201)));
		assertTrue(condition.satisfied(new Integer(230)));
		assertTrue(condition.satisfied(new Integer(50)));
		assertTrue(condition.satisfied(new Integer(10)));
		assertTrue(condition.satisfied(new Integer(35)));
		assertFalse(condition.satisfied(new Integer(0)));
		
		assertTrue(condition2.satisfied(new Integer(3)));
		assertTrue(condition2.satisfied(new Integer(6)));
		assertTrue(condition2.satisfied(new Integer(201)));
		assertTrue(condition2.satisfied(new Integer(230)));
		assertTrue(condition2.satisfied(new Integer(50)));
		assertTrue(condition2.satisfied(new Integer(11)));
		assertFalse(condition2.satisfied(new Integer(-1)));
		assertTrue(condition2.satisfied(new Integer(0)));
		
		assertFalse(condition3.satisfied(new Integer(3)));
		assertFalse(condition3.satisfied(new Integer(6)));
		assertTrue(condition3.satisfied(new Integer(201)));
		assertTrue(condition3.satisfied(new Integer(230)));
		assertTrue(condition3.satisfied(new Integer(100)));
		assertFalse(condition3.satisfied(new Integer(0)));
	}
}
