package yogi.server.gui.superuserpermissions.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoManager;

public class SuperUserForGetUserInfoListCommand extends CommandAdapter<List<UserInfo>> {

	private static final long serialVersionUID = 7166513806743562911L;

	public SuperUserForGetUserInfoListCommand(String userId) {
		super(userId);
	}

	@Override
	public List<UserInfo> execute() {
		User superUser = UserManager.get().getUser(getUserId());
		Set<User> users = SuperUserPermissionAssistant.get().getUsers(superUser);
		List<UserInfo> rtnValue = new ArrayList<UserInfo>(users.size());
		for(User user : users) {
			UserInfo userInfo = UserInfoManager.get().getUserInfo(user);
			if(userInfo!=null) rtnValue.add(userInfo);
		}
		Collections.sort(rtnValue, new Comparator<UserInfo>() {
			@Override
			public int compare(UserInfo o1, UserInfo o2) {
				return o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase());
			}
		});
		return rtnValue;
	}
}