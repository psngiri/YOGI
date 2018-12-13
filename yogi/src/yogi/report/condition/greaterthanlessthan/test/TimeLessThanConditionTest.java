package yogi.report.condition.greaterthanlessthan.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.time.TimeCreator;
import yogi.report.condition.time.TimeLessThanCondition;
import yogi.report.condition.time.TimeLessThanOrEqualsCondition;

import junit.framework.TestCase;

public class TimeLessThanConditionTest extends TestCase{
	
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

	public void testTimeLessThanCondition() {
		TimeLessThanCondition condition = new TimeLessThanCondition("00:10");
		TimeLessThanCondition condition2 = new TimeLessThanCondition("3:45");
		TimeLessThanCondition condition3 = new TimeLessThanCondition("06:01");
		TimeCreator tc = new TimeCreator();
		tc.setTime(3);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(6);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(230);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition.satisfied(tc.create()));
		
		tc.setTime(3);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(6);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(250);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(11);
		assertTrue(condition2.satisfied(tc.create()));
		
		tc.setTime(3);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(6);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(1000);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(11);
		assertTrue(condition3.satisfied(tc.create()));
	}
	
	public void testTimeLessThanOrEqualsCondition() {
		TimeLessThanOrEqualsCondition condition = new TimeLessThanOrEqualsCondition("00:10");
		TimeLessThanOrEqualsCondition condition2 = new TimeLessThanOrEqualsCondition("3:45");
		TimeLessThanOrEqualsCondition condition3 = new TimeLessThanOrEqualsCondition("06:01");
		TimeCreator tc = new TimeCreator();
		tc.setTime(3);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(6);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(230);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(50);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(10);
		assertTrue(condition.satisfied(tc.create()));
		
		tc.setTime(3);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(225);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(250);
		assertFalse(condition2.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition2.satisfied(tc.create()));
		tc.setTime(11);
		assertTrue(condition2.satisfied(tc.create()));
		
		tc.setTime(3);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(6);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(201);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(1000);
		assertFalse(condition3.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition3.satisfied(tc.create()));
		tc.setTime(11);
		assertTrue(condition3.satisfied(tc.create()));
	}
	
	
}
