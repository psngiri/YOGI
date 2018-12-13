package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.time.TimeCreator;
import yogi.report.condition.time.TimeGreaterThanCondition;
import yogi.report.condition.time.TimeGreaterThanOrEqualsCondition;

import junit.framework.TestCase;

public class TimeGreaterThanConditionTest extends TestCase{
	
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

	public void testTimeGreaterThanCondition() {
		TimeGreaterThanCondition condition = new TimeGreaterThanCondition("00:10");
		TimeGreaterThanCondition condition2 = new TimeGreaterThanCondition("3:45");
		TimeGreaterThanCondition condition3 = new TimeGreaterThanCondition("06:01");
		TimeCreator tc = new TimeCreator();
		tc.setTime(3);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(230);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(11);
		assertTrue(condition.satisfied(tc.create()));
		
		tc.setTime(3);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(250);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition2.satisfied(tc.create()));
		
		tc.setTime(3);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(1000);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition3.satisfied(tc.create()));
	}
	
	public void testTimeGreaterThanOrEqualsCondition() {
		TimeGreaterThanOrEqualsCondition condition = new TimeGreaterThanOrEqualsCondition("00:10");
		TimeGreaterThanOrEqualsCondition condition2 = new TimeGreaterThanOrEqualsCondition("3:45");
		TimeGreaterThanOrEqualsCondition condition3 = new TimeGreaterThanOrEqualsCondition("06:01");
		TimeCreator tc = new TimeCreator();
		tc.setTime(3);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(230);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(10);
		assertTrue(condition.satisfied(tc.create()));
		
		tc.setTime(3);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(225);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(250);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition2.satisfied(tc.create()));
		
		tc.setTime(3);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(1000);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition3.satisfied(tc.create()));
	}
	
	
}
