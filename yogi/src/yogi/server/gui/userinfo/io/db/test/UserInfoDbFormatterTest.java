package yogi.server.gui.userinfo.io.db.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.Executor;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.app.testing.TestModule;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.dump.DumpDbRecord;
import yogi.remote.CommandException;
import yogi.remote.client.app.BaseCommandExecutor;
import yogi.remote.client.app.CommandExecutor;
import yogi.remote.client.app.MultiServerCommandExecutor;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoManager;
import yogi.server.gui.userinfo.io.db.UserInfoDbFormatter;
import yogi.server.gui.userinfo.io.db.UserInfoDbReader;

public class UserInfoDbFormatterTest extends TestCase{
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		TestErrorReporter.start();
	}

	@Override
	protected void tearDown() throws Exception
	{
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	private void setUpData()
	{	
		MultiServerCommandExecutor.get().execute(new TestModule());
		
		BaseCommandExecutor.Initialized = true;
		CommandExecutor.UsePull = true;
	
		ArrayList<DbRecord> userInfo = new ArrayList<DbRecord>();
		userInfo.add(new DumpDbRecord("AA603427,Vikram,Vadavala"));
		userInfo.add(new DumpDbRecord("AA627674,Kishore,Mannava"));
		userInfo.add(new DumpDbRecord("AA966594,Jagan,Baliah"));
		UserInfoDbReader userInfoDbReader= new UserInfoDbReader(userInfo);
		Executor.get().execute(userInfoDbReader);
	
		TestModule testModule = new TestModule();
		testModule.run();
	}

	public void test() throws CommandException {
		setUpData();
	
		User user = UserManager.get().getUser("AA966594");
		UserInfo userInfo = UserInfoManager.get().getUserInfo(user);
		
		DbRecord record = new UserInfoDbFormatter().format(userInfo);
		System.out.println("Record \t: " + record);
	}
}
