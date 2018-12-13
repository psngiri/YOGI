package yogi.server.gui.superuserpermissions.io.db;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.superuserpermissions.SuperUserPermission;
import yogi.server.gui.superuserpermissions.SuperUserPermissionFactory;


public class SuperUserPermissionDbWriter extends FactoryWriter<SuperUserPermission> {

	public SuperUserPermissionDbWriter(DbResource resource, Selector<? super SuperUserPermission> selector) {
		super(SuperUserPermissionFactory.get(), new DbWriter<SuperUserPermission>(resource, new SuperUserPermissionDbFormatter()), selector);
	}
	
	public SuperUserPermissionDbWriter(DbResource resource) {
		this(resource, null);
	}
}
