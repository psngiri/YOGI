package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.DoubleGreaterThanCondition;
import yogi.report.condition.DoubleGreaterThanOrEqualsCondition;

import junit.framework.TestCase;

public class DoubleGreaterThanTest extends TestCase{
	
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

	public void testDoubleGreaterThanCondition() {
		DoubleGreaterThanCondition condition = new DoubleGreaterThanCondition("10");
		DoubleGreaterThanCondition condition2 = new DoubleGreaterThanCondition("0");
		DoubleGreaterThanCondition condition3 = new DoubleGreaterThanCondition("100");
		assertFalse(condition.satisfied(new Double(3)));
		assertFalse(condition.satisfied(new Double(6)));
		assertTrue(condition.satisfied(new Double(201)));
		assertTrue(condition.satisfied(new Double(230)));
		assertTrue(condition.satisfied(new Double(50)));
		assertTrue(condition.satisfied(new Double(11)));
		assertTrue(condition.satisfied(new Double(35)));
		assertFalse(condition.satisfied(new Double(0)));
		
		assertTrue(condition2.satisfied(new Double(3)));
		assertTrue(condition2.satisfied(new Double(6)));
		assertTrue(condition2.satisfied(new Double(201)));
		assertTrue(condition2.satisfied(new Double(230)));
		assertTrue(condition2.satisfied(new Double(50)));
		assertTrue(condition2.satisfied(new Double(11)));
		assertFalse(condition2.satisfied(new Double(-1)));
		assertFalse(condition2.satisfied(new Double(0)));
		
		assertFalse(condition3.satisfied(new Double(3)));
		assertFalse(condition3.satisfied(new Double(6)));
		assertTrue(condition3.satisfied(new Double(201)));
		assertTrue(condition3.satisfied(new Double(230)));
		assertFalse(condition3.satisfied(new Double(50)));
		assertFalse(condition3.satisfied(new Double(0)));
	}
	
	public void testDoubleGreaterThanOrEqualsCondition() {
		DoubleGreaterThanOrEqualsCondition condition = new DoubleGreaterThanOrEqualsCondition("10");
		DoubleGreaterThanOrEqualsCondition condition2 = new DoubleGreaterThanOrEqualsCondition("0");
		DoubleGreaterThanOrEqualsCondition condition3 = new DoubleGreaterThanOrEqualsCondition("100");
		assertFalse(condition.satisfied(new Double(3)));
		assertFalse(condition.satisfied(new Double(6)));
		assertTrue(condition.satisfied(new Double(201)));
		assertTrue(condition.satisfied(new Double(230)));
		assertTrue(condition.satisfied(new Double(50)));
		assertTrue(condition.satisfied(new Double(10)));
		assertTrue(condition.satisfied(new Double(35)));
		assertFalse(condition.satisfied(new Double(0)));
		
		assertTrue(condition2.satisfied(new Double(3)));
		assertTrue(condition2.satisfied(new Double(6)));
		assertTrue(condition2.satisfied(new Double(201)));
		assertTrue(condition2.satisfied(new Double(230)));
		assertTrue(condition2.satisfied(new Double(50)));
		assertTrue(condition2.satisfied(new Double(11)));
		assertFalse(condition2.satisfied(new Double(-1)));
		assertTrue(condition2.satisfied(new Double(0)));
		
		assertFalse(condition3.satisfied(new Double(3)));
		assertFalse(condition3.satisfied(new Double(6)));
		assertTrue(condition3.satisfied(new Double(201)));
		assertTrue(condition3.satisfied(new Double(230)));
		assertTrue(condition3.satisfied(new Double(100)));
		assertFalse(condition3.satisfied(new Double(0)));
	}
}
