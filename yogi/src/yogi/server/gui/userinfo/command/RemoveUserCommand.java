package yogi.server.gui.userinfo.command;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.Selector;
import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.db.command.QueryExecutorDMLCommand;
import yogi.base.util.logging.Logging;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserAssistant;
import yogi.server.gui.user.preferences.command.UserPreferencesDeleteCommand;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoFactory;
import yogi.server.gui.userinfo.UserInfoManager;

public class RemoveUserCommand extends QueryExecutorDMLCommand<Boolean> {
	private static final long serialVersionUID = 1L;
	private static String ANALYST_DELETE_QUERY="delete from analyst where ANL_EMP_ID in ";
	private static String USER_DELETE_QUERY="delete from user_group where userid in ";
	public static Level level = Level.SEVERE;
	private transient Logger logger;
	private String removeUserId;
	
	public RemoveUserCommand(String userId,String removeUserId) {
		super(userId);
		this.removeUserId = removeUserId;
	}

	@Override
	protected Boolean executeCommand() throws Exception {
		if(removeUserId==null || removeUserId.isEmpty()) return false;
		removeUserId = removeUserId.trim();
		List<UserInfo> userInfos = UserInfoManager.get().find(new Selector<UserInfo>(){
			@Override
			public boolean select(UserInfo item) {
				return UserAssistant.get().getEmployeeId(item.getUser()).equals(removeUserId);
			}
		});
		if(userInfos.size()<1) throw new RuntimeException("User "+removeUserId+" does not Exist");	
		boolean rtnValue=updateDatabase(userInfos);
		userInfos = new ArrayList<UserInfo>(userInfos);
		updateCacheServer(userInfos);
		if(rtnValue){
			StringBuilder usersStringBuilder=new StringBuilder();
			for(int i=0;i<userInfos.size();i++){
				UserInfo userInfo = userInfos.get(i);
				usersStringBuilder.append(userInfo.getUser().getId()).append("(").append(userInfo.getFirstName()).append(",").append(userInfo.getLastName()).append(")");
				if(i!=userInfos.size()-1) usersStringBuilder.append(",");
			}
			usersStringBuilder.trimToSize();
			if(logger==null) logger = Logging.getLogger(RemoveUserCommand.class);
			logger.log(level,"Users "+usersStringBuilder.toString()+" removed from System by "+getUserId());
		}
		return rtnValue;
	}
	
	/**
	 * @param userInfos
	 * @return
	 * @throws DBException
	 *  Deletes user from Analyst and usergroup table
	 */
	private boolean updateDatabase(List<UserInfo> userInfos) throws DBException{
		String deleteQuery = getDeleteQuery(userInfos, ANALYST_DELETE_QUERY);
		int numOfRows = QueryExecutor.get().executeUpdate(getDbResource(), deleteQuery);//deleting from analyst table
		String userDeleteQuery = getDeleteQuery(userInfos, USER_DELETE_QUERY);
		QueryExecutor.get().executeUpdate(getDbResource(), userDeleteQuery);//removing user from usergroup table
		if(numOfRows>0) return true;
		return false;
	}
	
	/**
	 * @param userInfos
	 * 	Delete UserPreferences,user from userGroups and UserInfo for the user
	 */
	private void updateCacheServer(List<UserInfo> userInfos) {
		for (UserInfo userInfo : userInfos) {
			User removeUser = userInfo.getUser();
			UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(removeUser);
			if (key == null) continue;
			String[] names = new String[1];
			names[0] = key.getIdName();
			UserPreferencesDeleteCommand command = new UserPreferencesDeleteCommand(names, removeUser.getId(), getUserId());
			command.execute();
			updateUserGroups(removeUser);
			UserInfoFactory.get().delete(userInfo);
		}
	}
	
	/**
	 * @param removeUser
	 * Removed user from UserGroups in Cache
	 */
	private void updateUserGroups(User removeUser){
		List<UserGroup> userGroups = new ArrayList<UserGroup>(UserGroupManager.get().getUserGroups(removeUser));
		for(UserGroup userGroup : userGroups){
			userGroup.removeUser(removeUser);
		}
	}
	
	private String getDeleteQuery(List<UserInfo> userInfos, String query){
		StringBuilder builder = new StringBuilder(query);
		for (int i=0;i<userInfos.size();i++) {
			if(i==0) builder.append("(");
			builder.append("'").append(userInfos.get(i).getUser().getId()).append("'");
			if(i!=userInfos.size()-1) {
				builder.append(",");
			}else{
				builder.append(")");
			}
		}
		builder.trimToSize();
		return builder.toString();
	}

}