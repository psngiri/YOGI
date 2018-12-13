package yogi.server.gui.applicationpermissions.io.db;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.applicationpermissions.ApplicationPermissions;
import yogi.server.gui.applicationpermissions.ApplicationPermissionsFactory;




public class ApplicationPermissionsDbWriter extends FactoryWriter<ApplicationPermissions> {

	public ApplicationPermissionsDbWriter(DbResource resource, Selector<? super ApplicationPermissions> selector)
	{
		super(ApplicationPermissionsFactory.get(), new DbWriter<ApplicationPermissions>(resource, new ApplicationPermissionsDbFormatter()), selector);
	}
	
	public ApplicationPermissionsDbWriter(DbResource resource)
	{
		this(resource, null);
	}
}
