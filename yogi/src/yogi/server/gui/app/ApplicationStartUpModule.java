package yogi.server.gui.app;

import yogi.base.app.BaseModule;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.applicationinfo.io.db.ApplicationInfoDbReader;
import yogi.server.gui.applicationpermissions.io.db.ApplicationPermissionsDbReader;
import yogi.server.gui.superuserpermissions.io.db.SuperUserPermissionDbReader;
import yogi.server.gui.user.preferences.app.UserPreferencesReaderModule;
import yogi.server.gui.usergroup.io.db.UserGroupDbReader;
import yogi.server.gui.userinfo.io.db.UserInfoDbReader;

public class ApplicationStartUpModule extends BaseModule {
	public static boolean RUN = true;
	private final DbResource dbResource;
	
	public ApplicationStartUpModule(DbResource ngpDbResource) {
		super();
		this.dbResource=ngpDbResource;
	}

	@Override
	public boolean isActivated() {
		return RUN;
	}

	@Override
	public void setup() {
		
		this.addReader(new UserInfoDbReader(dbResource));
		this.addReader(new UserGroupDbReader(dbResource));
		this.addReader(new SuperUserPermissionDbReader(dbResource));	
		this.addProcessor(new UserPreferencesReaderModule(dbResource));
		this.addReader(new ApplicationInfoDbReader(dbResource));
		this.addReader(new ApplicationPermissionsDbReader(dbResource));
	}

	
	
	
}