package yogi.server.gui.superuserpermissions.io.db.test;
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
import yogi.server.gui.applicationinfo.io.db.ApplicationInfoDbReader;
import yogi.server.gui.applicationpermissions.ApplicationPermissions;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsAssistant;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsManager;
import yogi.server.gui.applicationpermissions.io.db.ApplicationPermissionsDbReader;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.io.db.UserGroupDbReader;
import yogi.server.gui.userinfo.io.db.UserInfoDbReader;


public class SuperUserPermissionsReaderTest extends TestCase {
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
		userGroups.add(new DumpDbRecord("AAGroup1,AA966594"));
		userGroups.add(new DumpDbRecord("AAGroup1,AA627674"));
		userGroups.add(new DumpDbRecord("BAGroup,BA123459"));
		userGroups.add(new DumpDbRecord("BAGroup,BA123457"));
		userGroups.add(new DumpDbRecord("BAGroup2,BA123457"));
		UserGroupDbReader userGroupDbReader = new UserGroupDbReader(userGroups);
		
		ArrayList<DbRecord> applicationInfo = new ArrayList<DbRecord> ();
		applicationInfo.add(new DumpDbRecord("QueryTool,null"));
		applicationInfo.add(new DumpDbRecord("Strategy,null"));
		applicationInfo.add(new DumpDbRecord("AddonComparison,I"));
		ApplicationInfoDbReader applicationInfoDbReader = new ApplicationInfoDbReader(applicationInfo);
		
		ArrayList<DbRecord> applicationPermissions = new ArrayList<DbRecord> ();
		applicationPermissions.add(new DumpDbRecord("QueryTool,ALL,AAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("QueryTool,InternationalFareChanges,AAGroup1,F"));
		applicationPermissions.add(new DumpDbRecord("QueryTool,InternationalFareChanges,BAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("QueryTool,AddonFareCumulative,BAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("QueryTool,AddonFareChanges,BAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("QueryTool,AddonFareChanges,BAGroup2,F"));
		ApplicationPermissionsDbReader applicationPermissionsDbReader = new ApplicationPermissionsDbReader(applicationPermissions);
		
		TestModule testModule = new TestModule();
		testModule.addReader(userInfoDbReader);
		testModule.addReader(userGroupDbReader);
		testModule.addReader(applicationInfoDbReader);
		testModule.addReader(applicationPermissionsDbReader);
		testModule.run();
		
		assertEquals(6,ApplicationPermissionsManager.get().findAll().size());
		for(ApplicationPermissions permissions : ApplicationPermissionsManager.get().findAll()){
			System.out.println(permissions);
		}
		
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "InternationalFareChanges", UserManager.get().getUser("AA603427")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("AA603427")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareChanges", UserManager.get().getUser("AA603427")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "Record1Changes", UserManager.get().getUser("AA603427")));

		assertEquals(false,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "InternationalFareChanges", UserManager.get().getUser("AA627674")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("AA627674")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("AA627674")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareChanges", UserManager.get().getUser("AA627674")));

		assertEquals(false,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "InternationalFareChanges", UserManager.get().getUser("AA966594")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("AA966594")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareChanges", UserManager.get().getUser("AA966594")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "Record1Changes", UserManager.get().getUser("AA966594")));

		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "InternationalFareChanges", UserManager.get().getUser("BA123459")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("BA123459")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareChanges", UserManager.get().getUser("BA123459")));
		assertEquals(false,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "Record1Changes", UserManager.get().getUser("BA123459")));
		
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "InternationalFareChanges", UserManager.get().getUser("BA123457")));
		assertEquals(true,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareCumulative", UserManager.get().getUser("BA123457")));
		assertEquals(false,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "AddonFareChanges", UserManager.get().getUser("BA123457")));
		assertEquals(false,ApplicationPermissionsAssistant.get().isAuthorized("QueryTool", "Record1Changes", UserManager.get().getUser("BA123457")));
		
	}
}
