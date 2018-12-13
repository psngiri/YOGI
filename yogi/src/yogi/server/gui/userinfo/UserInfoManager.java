package yogi.server.gui.userinfo;

import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToOneInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.user.User;

/**
 * @author Vikram Vadavala
 *
 */
public class UserInfoManager extends RelationshipManager<UserInfo> {
	private static UserInfoManager itsInstance = new UserInfoManager();
	private static OneToOneInverseRelationship<User, UserInfo> userUserInfo = RelationshipTypeFactory.get().createOneToOneInverseRelationship(User.class, UserInfo.class,"UserInfo");

	protected UserInfoManager() {
		super();
	}

	public static UserInfoManager get() {
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(UserInfo userInfo) {
		this.buildRelationship(userInfo.getUser(), userInfo, userUserInfo);
	}

	@Override
	protected void deleteRelationships(UserInfo userInfo) {
		this.deleteRelationship(userInfo.getUser(), userInfo, userUserInfo);
	}

	public UserInfo getUserInfo(User user) {
		return this.getRelationship(user, userUserInfo);
	}

	
}
