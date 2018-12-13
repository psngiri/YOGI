package yogi.server.gui.usergroup.io.db.test;
/**
 * @author Vikram Vadavala
 *
 */
import java.util.ArrayList;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.app.testing.TestModule;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.dump.DumpDbRecord;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupManager;
import yogi.server.gui.usergroup.io.db.UserGroupDbReader;
import yogi.server.gui.userinfo.io.db.UserInfoDbReader;


public class UserGroupReaderTest extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
		TestErrorReporter.start();
	}

	protected void tearDown() throws Exception {
		TestErrorReporter.end();
		Factory.clearAllFactories();
		super.tearDown();
	}

	public void test() {
	
		ArrayList<DbRecord> userInfo = new ArrayList<DbRecord>();
		userInfo.add(new DumpDbRecord("AA603427,Vikram,Vadavala"));
		userInfo.add(new DumpDbRecord("AA627674,Kishore,Mannava"));
		userInfo.add(new DumpDbRecord("AA966594,Jagan,Baliah"));
		userInfo.add(new DumpDbRecord("BA123456,firstName6,lastName6"));
		userInfo.add(new DumpDbRecord("BA123457,firstName7,lastName7"));
		userInfo.add(new DumpDbRecord("BA123459,firstName9,lastName9"));
		UserInfoDbReader userInfoDbReader= new UserInfoDbReader(userInfo);
				
		ArrayList<DbRecord> userGroups = new ArrayList<DbRecord> ();
		userGroups.add(new DumpDbRecord("AAGroup,AA%"));
		userGroups.add(new DumpDbRecord("AAGroup1,AA603427"));
		userGroups.add(new DumpDbRecord("AAGroup1,BA123456"));
		userGroups.add(new DumpDbRecord("BAGroup,BA123459"));
		userGroups.add(new DumpDbRecord("BAGroup,BA123457"));
		UserGroupDbReader userGroupDbReader = new UserGroupDbReader(userGroups);
		
		TestModule testModule = new TestModule();
		testModule.addReader(userInfoDbReader);
		testModule.addReader(userGroupDbReader);
		testModule.run();
						
		assertEquals(3,UserGroupManager.get().getUserGroup("AAGroup").getUsers().size());
		assertEquals(2,UserGroupManager.get().getUserGroup("AAGroup1").getUsers().size());
		assertEquals(2,UserGroupManager.get().getUserGroup("BAGroup").getUsers().size());
		
		for(UserGroup userGroup : UserGroupManager.get().findAll()){
			System.out.println(userGroup);
		}
		
		UserGroup userGroupAA = UserGroupManager.get().getUserGroup("AAGroup");
		userGroupAA.addUser(UserManager.get().getUser("AA557505"));
		
		assertEquals(4,userGroupAA.getUsers().size());
		System.out.println(userGroupAA);
		
		userGroupAA.removeUser(UserManager.get().getUser("AA557505"));
		
		assertEquals(3,userGroupAA.getUsers().size());
		System.out.println(userGroupAA);

	}
}
