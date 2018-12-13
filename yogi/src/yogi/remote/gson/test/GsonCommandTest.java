package yogi.remote.gson.test;

import java.util.logging.Level;

import junit.framework.TestCase;

import yogi.base.app.Executor;
import yogi.base.app.testing.TestErrorReporter;
import yogi.remote.gson.GsonCommand;
import yogi.remote.test.TestCommand;
import com.google.gson.Gson;


public class GsonCommandTest extends TestCase{

	public void test()
	{
		TestCommand testCommand = new TestCommand(10, Level.INFO, "test");
		Gson gson = new Gson();
		String value = gson.toJson(testCommand);
		String name = TestCommand.class.getName();
		GsonCommand gsonCommand = new GsonCommand(name, value, null);
		String output = Executor.get().execute(gsonCommand);
		System.out.println(output);		
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		super.tearDown();
	}
}
