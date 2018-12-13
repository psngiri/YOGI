package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.time.TimeCreator;
import yogi.report.condition.time.TimeInCondition;

import junit.framework.TestCase;

public class TimeInConditionTest extends TestCase{
	
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

	public void testTimeInCondition() {
		TimeInCondition condition = new TimeInCondition("00:10,00:50,3:45,4:10,00:03,06:01");
		TimeCreator tc = new TimeCreator();
		tc.setTime(3);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(6);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(201);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(230);
		assertFalse(condition.satisfied(tc.create()));
		tc.setTime(50);
		assertTrue(condition.satisfied(tc.create()));
		tc.setTime(11);
		assertFalse(condition.satisfied(tc.create()));

	}
}
