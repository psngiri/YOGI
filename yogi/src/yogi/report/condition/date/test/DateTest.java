package yogi.report.condition.date.test;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import yogi.period.date.DateManager;
import yogi.report.condition.config.DateInConditionConfig;
import yogi.report.condition.config.DateNotInConditionConfig;

public class DateTest extends TestCase{
	
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
	
	public void testDateInCondition(){
		DateInConditionConfig date = new DateInConditionConfig();
		assertFalse(date.getValidator().validate("12ABC12,12XYX12"));
		assertFalse(date.getValidator().validate("12JAN12,12XYX12"));
		
		assertTrue(date.getValidator().validate("01DEC12,16Feb31,01jan12"));
		assertTrue(date.getValidator().validate("01dEC12,16FeB31,01jan12"));
		assertTrue(date.getValidator().validate("01JAN12,01jan12,01Jan12"));
		assertTrue(date.getValidator().validate("01FEB12,01feb12,01Feb12"));
		assertTrue(date.getValidator().validate("01MAR12,01mar12,01Mar12"));
		assertTrue(date.getValidator().validate("01APR12,01apr12,01Apr12"));
		assertTrue(date.getValidator().validate("01MAY12,01may12,01May12"));
		assertTrue(date.getValidator().validate("01JUN12,01jun12,01Jun12"));
		assertTrue(date.getValidator().validate("01JUL12,01jul12,01Jul12"));
		assertTrue(date.getValidator().validate("01AUG12,01aug12,01Aug12"));
		assertTrue(date.getValidator().validate("01SEP12,01sep12,01Sep12"));
		assertTrue(date.getValidator().validate("01OCT12,01oct12,01Oct12"));
		assertTrue(date.getValidator().validate("01NOV12,01nov12,01Nov12"));
		assertTrue(date.getValidator().validate("01DEC12,01dec12,01Dec12"));
		
		assertTrue(date.getValidator().validate("01DEC12"));
		
		assertFalse(date.getValidator().validate(""));
		assertFalse(date.getValidator().validate("122012"));
		assertTrue(date.getCondition("01DEC12,16FEB31,01JAN12").satisfied(DateManager.get().getDate(12, 11, 01))); //Test for 01DEC12
	}
	
	public void testDateNotInCondition(){
		DateNotInConditionConfig date = new DateNotInConditionConfig();
	//	assertTrue(date.getValidator().validate("120112,160231,011212"));
	//	assertTrue(date.getValidator().validate("120112"));
		assertFalse(date.getValidator().validate(""));
		assertFalse(date.getValidator().validate("122012"));
	}
}