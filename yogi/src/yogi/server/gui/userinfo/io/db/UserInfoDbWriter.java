package yogi.server.gui.userinfo.io.db;

import yogi.base.Selector;
import yogi.base.io.FactoryWriter;
import yogi.base.io.db.DbWriter;
import yogi.base.io.resource.db.DbResource;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoFactory;

public class UserInfoDbWriter extends FactoryWriter<UserInfo> {

	public UserInfoDbWriter(DbResource resource, Selector<? super UserInfo> selector)
	{
		super(UserInfoFactory.get(), new DbWriter<UserInfo>(resource, new UserInfoDbFormatter()), selector);
	}
	
	public UserInfoDbWriter(DbResource resource)
	{
		this(resource, null);
	}
}
