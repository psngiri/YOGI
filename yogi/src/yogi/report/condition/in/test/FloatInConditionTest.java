package yogi.report.condition.in.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.report.condition.FloatInCondition;

public class FloatInConditionTest extends TestCase{
	
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
	
//	public void testFloatFromFile() {
//		yogi.report.condition.BaseInCondition.UploadedDataFileLocation = "C:/temp/";
//		FloatInCondition condition = new FloatInCondition("File:my floats.txt:1");
//		assertTrue(condition.satisfied(new Float(3.6)));
//		assertTrue(condition.satisfied(new Float(6)));
//		assertTrue(condition.satisfied(new Float(11.3)));
//		assertFalse(condition.satisfied(new Float(11.34)));
//	}

	public void testFloatRangeInCondition() {
		FloatInCondition condition = new FloatInCondition("10-50,3,3.6,201");
		assertTrue(condition.satisfied(new Float(3.6)));
		assertFalse(condition.satisfied(new Float(6)));
		assertTrue(condition.satisfied(new Float(11.3)));
		assertTrue(condition.satisfied(new Float(11.34)));
		assertTrue(condition.satisfied(new Float(35)));		
		assertTrue(condition.satisfied(new Float(201)));
		assertTrue(condition.satisfied(new Float(50)));
		assertTrue(condition.satisfied(new Float(50.0)));
		assertFalse(condition.satisfied(new Float(301)));
	}
	
	public void testFloatInCondition() {
		FloatInCondition condition = new FloatInCondition("3.6,11.3,11.34,35,50,201");
		assertTrue(condition.satisfied(new Float(3.6)));
		assertFalse(condition.satisfied(new Float(6)));
		assertTrue(condition.satisfied(new Float(11.3)));
		assertTrue(condition.satisfied(new Float(11.34)));
		assertTrue(condition.satisfied(new Float(35)));		
		assertTrue(condition.satisfied(new Float(201)));
		assertTrue(condition.satisfied(new Float(50)));
		assertTrue(condition.satisfied(new Float(50.0)));
		assertFalse(condition.satisfied(new Float(301)));
	}


}
