package yogi.report.condition.in.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.config.IntegerListInConditionConfig;

public class IntegerListInConditionTest extends TestCase{
	
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
	
	public void testIntegerListInCondition() {
		List<Integer> value = new ArrayList<Integer>();
		value.add(3);
		value.add(3);
		value.add(3);
		assertTrue(new IntegerListInConditionConfig().getCondition("3^3^3").satisfied(value));
		assertFalse(new IntegerListInConditionConfig().getCondition("3^2^1").satisfied(value));
		value = new ArrayList<Integer>();
		value.add(1);
		value.add(2);
		value.add(3);		
		assertTrue(new IntegerListInConditionConfig().getCondition("3^3^3,1^2^3").satisfied(value));
		value = new ArrayList<Integer>();
		value.add(1);
		value.add(2);
		assertTrue(new IntegerListInConditionConfig().getCondition("3^3^3,1^2^3,1^2").satisfied(value));	
	}

}
