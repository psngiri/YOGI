package yogi.report.condition.in.test;

import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.report.condition.StringInCondition;
import yogi.report.server.config.validator.StringInValidator;

import junit.framework.TestCase;

public class StringInConditionFileTest extends TestCase{
	
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
	
	public void test() {
		StringInCondition condition = new StringInCondition("3.6,6,11.3");
		assertTrue(condition.satisfied("3.6"));
		assertTrue(condition.satisfied("6"));
		assertTrue(condition.satisfied("11.3"));
		assertFalse(condition.satisfied("11.34"));
	}

	public void testFromFile() {
	yogi.report.condition.BaseInCondition.UploadedDataFileLocation = "C:/temp/";
		StringInCondition condition = new StringInCondition("File:my floats.txt:1");
		assertTrue(condition.satisfied("3.6"));
		assertTrue(condition.satisfied("6"));
		assertTrue(condition.satisfied("11.3"));
		assertFalse(condition.satisfied("11.34"));
		StringInValidator validator = new StringInValidator();
		validator.validate("File:my floats.txt:1");
	}
	
	public void testFromQuery() {
		BaseCommandExecutor.Initialized = true;
		yogi.remote.client.app.MultiServerCommandExecutor.setServerTypeEqualsCommandServerAddressesColonPortNumbers("IFS=cpappt01.qcorpaa.aa.com:7080,InternationalPRE=cpappt01.qcorpaa.aa.com:7080,Paging=cpappt01.qcorpaa.aa.com:7081,Strategy=cpappt01.qcorpaa.aa.com:7082,GuiServer=cpappt01.qcorpaa.aa.com:7082");
		StringInCondition condition = new StringInCondition("Query:MYODTESTQuery:OD:AA627674:I");
		StringInValidator validator = new StringInValidator();
		validator.validate("File:my floats.txt:1");
	}

}
