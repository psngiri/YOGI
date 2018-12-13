package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.DoubleLessThanCondition;
import yogi.report.condition.DoubleLessThanOrEqualsCondition;

import junit.framework.TestCase;

public class DoubleLessThanTest extends TestCase{
	
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

	public void testDoubleLessThanCondition() {
		DoubleLessThanCondition condition = new DoubleLessThanCondition("10");
		DoubleLessThanCondition condition2 = new DoubleLessThanCondition("0");
		DoubleLessThanCondition condition3 = new DoubleLessThanCondition("100");
		assertTrue(condition.satisfied(new Double(3)));
		assertTrue(condition.satisfied(new Double(6)));
		assertFalse(condition.satisfied(new Double(201)));
		assertFalse(condition.satisfied(new Double(230)));
		assertFalse(condition.satisfied(new Double(50)));
		assertFalse(condition.satisfied(new Double(11)));
		assertFalse(condition.satisfied(new Double(35)));
		assertTrue(condition.satisfied(new Double(0)));
		
		assertFalse(condition2.satisfied(new Double(3)));
		assertFalse(condition2.satisfied(new Double(6)));
		assertFalse(condition2.satisfied(new Double(201)));
		assertFalse(condition2.satisfied(new Double(230)));
		assertFalse(condition2.satisfied(new Double(50)));
		assertFalse(condition2.satisfied(new Double(11)));
		assertTrue(condition2.satisfied(new Double(-1)));
		assertFalse(condition2.satisfied(new Double(0)));
	
		assertTrue(condition3.satisfied(new Double(3)));
		assertTrue(condition3.satisfied(new Double(6)));
		assertFalse(condition3.satisfied(new Double(201)));
		assertFalse(condition3.satisfied(new Double(230)));
		assertTrue(condition3.satisfied(new Double(50)));
		assertTrue(condition3.satisfied(new Double(0)));
	}
	
	public void testDoubleLessThanOrEqualsCondition() {
		DoubleLessThanOrEqualsCondition condition = new DoubleLessThanOrEqualsCondition("10");
		DoubleLessThanOrEqualsCondition condition2 = new DoubleLessThanOrEqualsCondition("0");
		DoubleLessThanOrEqualsCondition condition3 = new DoubleLessThanOrEqualsCondition("100");
		assertTrue(condition.satisfied(new Double(3)));
		assertTrue(condition.satisfied(new Double(6)));
		assertFalse(condition.satisfied(new Double(201)));
		assertFalse(condition.satisfied(new Double(230)));
		assertFalse(condition.satisfied(new Double(50)));
		assertFalse(condition.satisfied(new Double(11)));
		assertFalse(condition.satisfied(new Double(35)));
		assertTrue(condition.satisfied(new Double(0)));
		
		assertFalse(condition2.satisfied(new Double(3)));
		assertFalse(condition2.satisfied(new Double(6)));
		assertFalse(condition2.satisfied(new Double(201)));
		assertFalse(condition2.satisfied(new Double(230)));
		assertFalse(condition2.satisfied(new Double(50)));
		assertFalse(condition2.satisfied(new Double(11)));
		assertTrue(condition2.satisfied(new Double(-1)));
		assertTrue(condition2.satisfied(new Double(0)));
	
		assertTrue(condition3.satisfied(new Double(3)));
		assertTrue(condition3.satisfied(new Double(6)));
		assertFalse(condition3.satisfied(new Double(201)));
		assertFalse(condition3.satisfied(new Double(230)));
		assertTrue(condition3.satisfied(new Double(50)));
		assertTrue(condition3.satisfied(new Double(0)));;
	}
}
