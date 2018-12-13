package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.FloatGreaterThanCondition;
import yogi.report.condition.FloatGreaterThanOrEqualsCondition;

import junit.framework.TestCase;

public class FloatGreaterThanTest extends TestCase{
	
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

	public void testFloatGreaterThanCondition() {
		FloatGreaterThanCondition condition = new FloatGreaterThanCondition("10");
		FloatGreaterThanCondition condition2 = new FloatGreaterThanCondition("0");
		FloatGreaterThanCondition condition3 = new FloatGreaterThanCondition("100");
		assertFalse(condition.satisfied(new Float(3)));
		assertFalse(condition.satisfied(new Float(6)));
		assertTrue(condition.satisfied(new Float(201)));
		assertTrue(condition.satisfied(new Float(230)));
		assertTrue(condition.satisfied(new Float(50)));
		assertTrue(condition.satisfied(new Float(11)));
		assertTrue(condition.satisfied(new Float(35)));
		assertFalse(condition.satisfied(new Float(0)));
		
		assertTrue(condition2.satisfied(new Float(3)));
		assertTrue(condition2.satisfied(new Float(6)));
		assertTrue(condition2.satisfied(new Float(201)));
		assertTrue(condition2.satisfied(new Float(230)));
		assertTrue(condition2.satisfied(new Float(50)));
		assertTrue(condition2.satisfied(new Float(11)));
		assertFalse(condition2.satisfied(new Float(-1)));
		assertFalse(condition2.satisfied(new Float(0)));
		
		assertFalse(condition3.satisfied(new Float(3)));
		assertFalse(condition3.satisfied(new Float(6)));
		assertTrue(condition3.satisfied(new Float(201)));
		assertTrue(condition3.satisfied(new Float(230)));
		assertFalse(condition3.satisfied(new Float(50)));
		assertFalse(condition3.satisfied(new Float(0)));
	}
	
	public void testFloatGreaterThanOrEqualsCondition() {
		FloatGreaterThanOrEqualsCondition condition = new FloatGreaterThanOrEqualsCondition("10");
		FloatGreaterThanOrEqualsCondition condition2 = new FloatGreaterThanOrEqualsCondition("0");
		FloatGreaterThanOrEqualsCondition condition3 = new FloatGreaterThanOrEqualsCondition("100");
		assertFalse(condition.satisfied(new Float(3)));
		assertFalse(condition.satisfied(new Float(6)));
		assertTrue(condition.satisfied(new Float(201)));
		assertTrue(condition.satisfied(new Float(230)));
		assertTrue(condition.satisfied(new Float(50)));
		assertTrue(condition.satisfied(new Float(10)));
		assertTrue(condition.satisfied(new Float(35)));
		assertFalse(condition.satisfied(new Float(0)));
		
		assertTrue(condition2.satisfied(new Float(3)));
		assertTrue(condition2.satisfied(new Float(6)));
		assertTrue(condition2.satisfied(new Float(201)));
		assertTrue(condition2.satisfied(new Float(230)));
		assertTrue(condition2.satisfied(new Float(50)));
		assertTrue(condition2.satisfied(new Float(11)));
		assertFalse(condition2.satisfied(new Float(-1)));
		assertTrue(condition2.satisfied(new Float(0)));
		
		assertFalse(condition3.satisfied(new Float(3)));
		assertFalse(condition3.satisfied(new Float(6)));
		assertTrue(condition3.satisfied(new Float(201)));
		assertTrue(condition3.satisfied(new Float(230)));
		assertTrue(condition3.satisfied(new Float(100)));
		assertFalse(condition3.satisfied(new Float(0)));
	}
}
