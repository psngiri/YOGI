package yogi.server.gui.userinfo.command;

import java.util.logging.Level;
import java.util.logging.Logger;

import yogi.base.app.Executor;
import yogi.base.io.db.DBException;
import yogi.base.io.db.QueryExecutor;
import yogi.base.io.db.command.QueryExecutorDMLCommand;
import yogi.base.util.logging.Logging;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.command.UserPreferencesSaveCommand;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.usergroup.UserGroup;
import yogi.server.gui.usergroup.UserGroupManager;
import yogi.server.gui.userinfo.UserInfo;
import yogi.server.gui.userinfo.UserInfoCreator;
import yogi.server.gui.userinfo.UserInfoFactory;
import yogi.server.gui.userinfo.UserInfoManager;
import yogi.server.gui.userinfo.UserInfoValidator;

public class CreateUserCommand extends QueryExecutorDMLCommand<Boolean> {
	private static final long serialVersionUID = 1L;
	public static String query="Insert into analyst (ANL_EMP_ID,ANL_FST_NM,ANL_LST_NM,ANL_ATPCO_ID,ANL_ORACLE_ID,ATTRIBUTES) values (?,?,?,?,?,'TYPE:analyst TYPE:analyst')";
	public static Level level = Level.SEVERE;
	private transient Logger logger;
	private String firstName;
	private String lastName;
	private String atpcoUserId;
	private String replicateUserId;
	private String createUserId;
	
	public CreateUserCommand(String userId,String createUserId, String firstName, String lastName, String atpcoUserId, String replicateUser) {
		super(userId);
		this.createUserId = createUserId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.atpcoUserId = atpcoUserId;
		this.replicateUserId = replicateUser;
	}

	@Override
	protected Boolean executeCommand() throws Exception {
		try {
			createUserId = getCreateUserId().trim();
			firstName = firstName.trim();
			lastName = lastName.trim();
			if(getCreateUserId().isEmpty() || firstName.isEmpty() || lastName.isEmpty() || replicateUserId.isEmpty()) return false;
			
			User user = UserManager.get().getUser(getCreateUserId());
			if(UserInfoManager.get().getUserInfo(user) != null) return false;
			
			User replicateuser = UserManager.get().getUser(replicateUserId);
			if(UserInfoManager.get().getUserInfo(replicateuser) == null) throw new RuntimeException("Replicate User "+replicateuser.getId()+ " does not Exist");
			UserPreferences userPreferences = getUserPreferences(replicateuser);
			if(userPreferences==null) throw new RuntimeException("UserPreferences does not Exist for Replicate User "+replicateuser.getId());
					
			updateUserGroups(user, replicateuser);
			saveUserPreferences(userPreferences);
			
			UserInfoCreator creator = new UserInfoCreator();
			creator.setUser(user);
			creator.setFirstName(firstName);
			creator.setLastName(lastName);
			int numOfRows = QueryExecutor.get().executeUpdate(getDbResource(), query, getCreateUserId(), firstName, lastName, atpcoUserId, getCreateUserId());
			UserInfo userInfo = UserInfoFactory.get().create(creator, new UserInfoValidator());
			if(userInfo != null && numOfRows >0) {
				if(logger==null) logger = Logging.getLogger(CreateUserCommand.class);
				logger.log(level,"New User "+userInfo.getUser().getId()+"("+userInfo.getFirstName()+","+userInfo.getLastName()+")"+" created by "+getUserId());
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException("Error in creating user. Please contact admin."+e.getMessage());
		}
		return false;
	}

	private String getCreateUserId() {
		return createUserId;
	}
	
	private void saveUserPreferences(UserPreferences userPreferences){	
		UserPreferencesSaveCommand command = new UserPreferencesSaveCommand(getCreateUserId(), getUserId(), userPreferences.getData());
		Executor.get().execute(command);
	}

	private UserPreferences getUserPreferences(User user) {
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(user);
		if(key==null) return null;
		UserPreferences latestRecord = UserPreferencesManager.get().getLatestRecord(key);
		return latestRecord;
	}
	
	private void updateUserGroups(User user, User replicateUser) throws DBException{
		for(UserGroup userGroup : UserGroupManager.get().getUserGroups(replicateUser)){
			userGroup.addUser(user);
		}
		QueryExecutor.get().executeUpdate(getDbResource(), getInsertQuery(), replicateUser.getId());	
	}
	
	private String getInsertQuery(){
		return "insert into user_group (group_name, userid) select group_name,"+"'"+getCreateUserId()+"'"+ " from user_group where userid=?";
	}
}
