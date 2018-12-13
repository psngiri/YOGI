package yogi.server.gui.superuserpermissions.io.db;

import java.util.Collection;

import yogi.base.io.DefaultRecordProcessor;
import yogi.base.io.db.DbRecord;
import yogi.base.io.db.DefaultDbRecordReader;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.superuserpermissions.SuperUserPermission;
import yogi.server.gui.superuserpermissions.SuperUserPermissionFactory;
import yogi.server.gui.superuserpermissions.SuperUserPermissionValidator;


public class SuperUserPermissionDbReader extends DefaultDbRecordReader<SuperUserPermission> {
	
	public static boolean RUN = true;
	
	public SuperUserPermissionDbReader(DbResource resource) {
		super(resource);
		setup();
	}

	private void setup() {
		DefaultRecordProcessor<SuperUserPermission, DbRecord> recordProcessor = new DefaultRecordProcessor<SuperUserPermission, DbRecord>(new SuperUserPermissionValidator(), new SuperUserPermissionDbScanner(), SuperUserPermissionFactory.get(), new SuperUserPermissionDbRecordSelector());
		recordProcessor.setClearFactoryBeforeReading(true);
		this.addRecordProcessor(recordProcessor);
	}

	public SuperUserPermissionDbReader(Collection<DbRecord> superUserPermissions) {
		super(superUserPermissions);
		setup();
	}
	
	@Override
	public boolean isActivated() {
		return RUN;
	}
}
