package yogi.server.gui.user.preferences.test;
/**
 * @author Vikram Vadavala
 *
 */
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import yogi.base.Factory;
import yogi.base.app.testing.TestErrorReporter;
import yogi.base.app.testing.TestModule;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.dump.DumpDbRecord;
import yogi.server.gui.applicationinfo.io.db.ApplicationInfoDbReader;
import yogi.server.gui.applicationpermissions.ApplicationPermissions;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsManager;
import yogi.server.gui.applicationpermissions.io.db.ApplicationPermissionsDbReader;
import yogi.server.gui.user.preferences.UserPreferencesFieldsAssistant;
import yogi.server.gui.usergroup.io.db.UserGroupDbReader;
import yogi.server.gui.userinfo.io.db.UserInfoDbReader;


public class UserPreferencesPermissionsTest extends TestCase {
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
		applicationInfo.add(new DumpDbRecord("UserPreferences,null"));
		ApplicationInfoDbReader applicationInfoDbReader = new ApplicationInfoDbReader(applicationInfo);
		
		ArrayList<DbRecord> applicationPermissions = new ArrayList<DbRecord> ();
		applicationPermissions.add(new DumpDbRecord("UserPreferences,ALL,AAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("UserPreferences,hostCarrier,BAGroup,T"));
		applicationPermissions.add(new DumpDbRecord("UserPreferences,lowYield,BAGroup,T"));
		ApplicationPermissionsDbReader applicationPermissionsDbReader = new ApplicationPermissionsDbReader(applicationPermissions);
		
		TestModule testModule = new TestModule();
		testModule.addReader(userInfoDbReader);
		testModule.addReader(userGroupDbReader);
		testModule.addReader(applicationInfoDbReader);
		testModule.addReader(applicationPermissionsDbReader);
		testModule.run();
		
		assertEquals(3,ApplicationPermissionsManager.get().findAll().size());
		for(ApplicationPermissions permissions : ApplicationPermissionsManager.get().findAll()){
			System.out.println(permissions);
		}
		
		List<String> disabledFields1 = UserPreferencesFieldsAssistant.get().getDisabledFields("AA603427");
		System.out.println("AA603427");
		for(String field : disabledFields1){
			System.out.println(field);
		}
		System.out.println("");
		
		List<String> disabledFields2 = UserPreferencesFieldsAssistant.get().getDisabledFields("BA123457");
		System.out.println("BA123457");
		for(String field : disabledFields2){
			System.out.println(field);
		}
		System.out.println("");
		
		List<String> disabledFields3 = UserPreferencesFieldsAssistant.get().getDisabledFields("BA123456");
		System.out.println("BA123456");
		for(String field : disabledFields3){
			System.out.println(field);
		}
		System.out.println("");
		
		assertEquals(0,disabledFields1.size());
		assertEquals(4,disabledFields2.size());
		assertEquals(6,disabledFields3.size());

	}
}
