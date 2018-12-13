package yogi.server.gui.usergroup.io.db;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupFactory;




public class UserGroupDbWriter extends FactoryWriter<UserGroup> {

	public UserGroupDbWriter(DbResource resource, Selector<? super UserGroup> selector)
	{
		super(UserGroupFactory.get(), new DbWriter<UserGroup>(resource, new UserGroupDbFormatter()), selector);
	}
	
	public UserGroupDbWriter(DbResource resource)
	{
		this(resource, null);
	}
}
