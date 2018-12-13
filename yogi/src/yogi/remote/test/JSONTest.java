package yogi.remote.test;

import java.util.logging.Level;

import junit.framework.TestCase;

import yogi.base.app.testing.TestErrorReporter;
import com.google.gson.Gson;

public class JSONTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
	}

	public void test()
	{
		TestCommand testCommand = new TestCommand(10, Level.ALL, "test1");
		testCommand.execute();
		Gson gson = new Gson();
		String json = gson.toJson(testCommand);
		System.out.println(json);
		TestCommand fromJson = gson.fromJson(json, TestCommand.class);
		System.out.println(fromJson);
		assertEquals(10,fromJson.execute().intValue());
	}
}
