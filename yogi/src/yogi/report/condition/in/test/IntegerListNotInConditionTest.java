package yogi.report.condition.in.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.config.IntegerListNotInConditionConfig;

public class IntegerListNotInConditionTest extends TestCase{
	
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
	
	public void testIntegerListNotInCondition() {
		List<Integer> value = new ArrayList<Integer>();
		value.add(3);
		value.add(3);
		value.add(3);
		assertFalse(new IntegerListNotInConditionConfig().getCondition("3^3^3").satisfied(value));
		assertTrue(new IntegerListNotInConditionConfig().getCondition("3^2^1").satisfied(value));
	}

}
