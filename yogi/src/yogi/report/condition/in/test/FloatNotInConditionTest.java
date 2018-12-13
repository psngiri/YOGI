package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.FloatNotInCondition;

public class FloatNotInConditionTest extends TestCase{
	
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

	public void testFloatNotInCondition() {
		FloatNotInCondition condition = new FloatNotInCondition("10-50,225-250,3,3.6,201");
		assertFalse(condition.satisfied(new Float(3.6)));
		assertTrue(condition.satisfied(new Float(6)));
		assertFalse(condition.satisfied(new Float(11.3)));
		assertFalse(condition.satisfied(new Float(11.34)));
		assertFalse(condition.satisfied(new Float(35)));		
		assertFalse(condition.satisfied(new Float(201)));
		assertFalse(condition.satisfied(new Float(50)));
		assertFalse(condition.satisfied(new Float(50.0)));
		assertFalse(condition.satisfied(new Float(230.0)));		
		assertTrue(condition.satisfied(new Float(301)));
	}


}
