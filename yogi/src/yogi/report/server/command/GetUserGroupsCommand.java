package yogi.report.server.command;

import java.util.ArrayList;
import java.util.List;

import yogi.base.util.immutable.ImmutableList;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupManager;

public class GetUserGroupsCommand extends CommandAdapter<List<String>> {

	private static final long serialVersionUID = 3121828568281563935L;

	public GetUserGroupsCommand(String userId) {
		super(userId);
	}

	@Override
	public List<String> execute() {
		ImmutableList<UserGroup> userGroups = UserGroupManager.get().findAll();
		List<String> userGroupNames = new ArrayList<String>();
		for(UserGroup userGroup : userGroups){
			userGroupNames.add(userGroup.getGroupName());
		}
		return userGroupNames;
	}

}
