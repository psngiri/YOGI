package yogi.server.gui.applicationpermissions.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.applicationpermissions.ApplicationPermissions;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsFactory;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsValidator;


public class ApplicationPermissionsDbReader extends DefaultDbRecordReader<ApplicationPermissions> {
	public static boolean RUN = true;
	public ApplicationPermissionsDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		this.addRecordProcessor(new DefaultRecordProcessor<ApplicationPermissions, DbRecord>(new ApplicationPermissionsValidator(), new ApplicationPermissionsDbScanner(), ApplicationPermissionsFactory.get(), new ApplicationPermissionsDbRecordSelector()));
	}

	public ApplicationPermissionsDbReader(Collection<DbRecord> applicationPermissionss) {
		super(applicationPermissionss);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
