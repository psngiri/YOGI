package yogi.server.gui.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import yogi.base.util.immutable.ImmutableList;
import yogi.remote.client.app.CommandAdapter;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.record.key.Key;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.usergroup.UserGroupAssistant;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoManager;
import yogi.server.gui.userpartition.UserPartition;
import yogi.server.gui.userpartition.UserPartitionManager;


public abstract class TypeGetUsersCommand<K extends Key> extends CommandAdapter<List<UserInfo>> {

	private static final long serialVersionUID = -6849748273507429406L;
	
	public TypeGetUsersCommand(String userId) {
		super(userId);
	}

	@Override
	public List<UserInfo> execute() {
		User loginUser = UserManager.get().getUser(getUserId());
		Partition partition = PartitionAssistant.get().getUserPartition(loginUser).getPartition();
		ImmutableList<UserPartition> userPartitions = UserPartitionManager.get().getUserPartitions(partition);
		List<UserInfo> rtnValue = new ArrayList<UserInfo>();
		KeyManager<K> keyManager = getKeyManager();
		Set<User> usersInLoginUserGroups = UserGroupAssistant.get().getUsersInLoginUserGroups(loginUser);
		for(UserPartition userPartition: userPartitions)
		{			
			if(!keyManager.getKeys(userPartition.getUser(), userPartition.getPartition()).isEmpty() && (usersInLoginUserGroups.contains(userPartition.getUser()))){//only return Users who are in Login User's User Groups
				UserInfo userInfo = UserInfoManager.get().getUserInfo(userPartition.getUser());
				if(userInfo!=null)rtnValue.add(userInfo);
			}
		}
		Collections.sort(rtnValue, new Comparator<UserInfo>(){
			@Override
			public int compare(UserInfo o1, UserInfo o2) {
				return o1.getLastName().toLowerCase().compareTo(o2.getLastName().toLowerCase());
			}
		});
		return rtnValue;
	}
	
	
	public abstract KeyManager<K> getKeyManager();
}