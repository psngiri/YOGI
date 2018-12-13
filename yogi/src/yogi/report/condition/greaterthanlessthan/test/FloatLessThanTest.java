package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.FloatLessThanCondition;
import yogi.report.condition.FloatLessThanOrEqualsCondition;

import junit.framework.TestCase;

public class FloatLessThanTest extends TestCase{
	
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

	public void testFloatLessThanCondition() {
		FloatLessThanCondition condition = new FloatLessThanCondition("10");
		FloatLessThanCondition condition2 = new FloatLessThanCondition("0");
		FloatLessThanCondition condition3 = new FloatLessThanCondition("100");
		assertTrue(condition.satisfied(new Float(3)));
		assertTrue(condition.satisfied(new Float(6)));
		assertFalse(condition.satisfied(new Float(201)));
		assertFalse(condition.satisfied(new Float(230)));
		assertFalse(condition.satisfied(new Float(50)));
		assertFalse(condition.satisfied(new Float(11)));
		assertFalse(condition.satisfied(new Float(35)));
		assertTrue(condition.satisfied(new Float(0)));
		
		assertFalse(condition2.satisfied(new Float(3)));
		assertFalse(condition2.satisfied(new Float(6)));
		assertFalse(condition2.satisfied(new Float(201)));
		assertFalse(condition2.satisfied(new Float(230)));
		assertFalse(condition2.satisfied(new Float(50)));
		assertFalse(condition2.satisfied(new Float(11)));
		assertTrue(condition2.satisfied(new Float(-1)));
		assertFalse(condition2.satisfied(new Float(0)));
	
		assertTrue(condition3.satisfied(new Float(3)));
		assertTrue(condition3.satisfied(new Float(6)));
		assertFalse(condition3.satisfied(new Float(201)));
		assertFalse(condition3.satisfied(new Float(230)));
		assertTrue(condition3.satisfied(new Float(50)));
		assertTrue(condition3.satisfied(new Float(0)));
	}
	
	public void testFloatLessThanOrEqualsCondition() {
		FloatLessThanOrEqualsCondition condition = new FloatLessThanOrEqualsCondition("10");
		FloatLessThanOrEqualsCondition condition2 = new FloatLessThanOrEqualsCondition("0");
		FloatLessThanOrEqualsCondition condition3 = new FloatLessThanOrEqualsCondition("100");
		assertTrue(condition.satisfied(new Float(3)));
		assertTrue(condition.satisfied(new Float(6)));
		assertFalse(condition.satisfied(new Float(201)));
		assertFalse(condition.satisfied(new Float(230)));
		assertFalse(condition.satisfied(new Float(50)));
		assertFalse(condition.satisfied(new Float(11)));
		assertFalse(condition.satisfied(new Float(35)));
		assertTrue(condition.satisfied(new Float(0)));
		
		assertFalse(condition2.satisfied(new Float(3)));
		assertFalse(condition2.satisfied(new Float(6)));
		assertFalse(condition2.satisfied(new Float(201)));
		assertFalse(condition2.satisfied(new Float(230)));
		assertFalse(condition2.satisfied(new Float(50)));
		assertFalse(condition2.satisfied(new Float(11)));
		assertTrue(condition2.satisfied(new Float(-1)));
		assertTrue(condition2.satisfied(new Float(0)));
	
		assertTrue(condition3.satisfied(new Float(3)));
		assertTrue(condition3.satisfied(new Float(6)));
		assertFalse(condition3.satisfied(new Float(201)));
		assertFalse(condition3.satisfied(new Float(230)));
		assertTrue(condition3.satisfied(new Float(50)));
		assertTrue(condition3.satisfied(new Float(0)));;
	}
}
