package yogi.remote.client.app.test;

import java.util.logging.Level;

import yogi.base.app.testing.TestErrorReporter;
import yogi.base.util.JsonAssistant;
import yogi.property.Property;
import yogi.property.PropertyImpl;
import yogi.property.PropertyManager;
import yogi.remote.CommandException;
import yogi.remote.client.app.CommandExecutor;
import yogi.remote.client.app.MultiServerCommandExecutor;

import junit.framework.TestCase;


public class MultiServerCommandExecutorTestServer extends TestCase{

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

	public void testServerType() throws CommandException
	{
		MultiServerCommandExecutor.setServerTypeEqualsCommandServerAddressesColonPortNumbers("ReportServer=localhost:6080");
		MultiServerCommandExecutor.ServerType.clear();
		CommandExecutor.Initialized = true;
		MultiServerCommandExecutor.ServerType.add(MultiServerCommandExecutor.DefaultServer);
		MultiServerCommandExecutor.ServerType.add("ReportServer");
		
		TestCommand testCommand = new TestCommand(10, Level.INFO);
		Integer rtnValue = MultiServerCommandExecutor.get().execute(testCommand);
		assertEquals(10, rtnValue.intValue());
		rtnValue = MultiServerCommandExecutor.get().execute("ReportServer", testCommand);
		assertEquals(10, rtnValue.intValue());
		System.out.println(JsonAssistant.get().toJson(MultiServerCommandExecutor.ServerType));
		Property property = new PropertyImpl("yogi.remote.client.app.MultiServerCommandExecutor", "ServerType", true, "[\"DefaultServer\",\"ReportServer\"]");
		PropertyManager.assignProperty(property);
		System.out.println(JsonAssistant.get().toJson(MultiServerCommandExecutor.ServerType));
	}

	
}
