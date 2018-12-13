package yogi.server.gui.user.preferences.command;

import yogi.server.gui.command.TypeSaveCommand;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.superuserpermissions.SuperUserPermissionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesCreator;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesFactory;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.UserPreferencesValidator;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;


public class UserPreferencesSaveCommand extends TypeSaveCommand<UserPreferencesKey,UserPreferencesData, UserPreferences, UserPreferencesCreator> {
	private static final long serialVersionUID = -3338090347106604543L;

	private static final UserPreferencesValidator UserPreferences_Validator = new UserPreferencesValidator();
	private transient UserPreferencesCreator userPreferencesCreator;

	public UserPreferencesSaveCommand(String userId,UserPreferencesData userPreferencesData) {
		super(UserPreferencesKeyManager.MY_PREFERENCES, userId,false,"","",userPreferencesData);
	}
	
	public UserPreferencesSaveCommand(String userId, String loginUserId, UserPreferencesData userPreferencesData) {
		this(userId, userPreferencesData);
		this.loginUserId = loginUserId;
	}

	@Override
	public UserPreferencesValidator getValidator() {
		return UserPreferences_Validator;
	}

	@Override
	public UserPreferencesKeyManager getKeyManager() {
		return UserPreferencesKeyManager.get();
	}
	
	@Override
	public UserPreferencesManager getRecordManager() {
		return  UserPreferencesManager.get();
	}

	@Override
	public UserPreferencesFactory getFactory() {
		return UserPreferencesFactory.get();
	}

	@Override
	protected UserPreferencesCreator getCreator() {
		if(userPreferencesCreator == null) userPreferencesCreator = new UserPreferencesCreator();
		return userPreferencesCreator;
	}

	@Override
	public Boolean execute() {
		User superUser = UserManager.get().getUser(getUserId());
		if(SuperUserPermissionAssistant.get().isValidSuperUser(superUser, getData())) {
			super.execute();
		}
		User pseudoUser = SuperUserPermissionAssistant.get().getUser(superUser);
		if(pseudoUser != superUser) {
			SuperUserPermissionAssistant.get().setSuperUser(superUser, pseudoUser); //For new design
			Partition superUserPartition = PartitionAssistant.get().getUserPartition(superUser).getPartition();
			Partition pseudoUserPartition = PartitionAssistant.get().getUserPartition(pseudoUser).getPartition();
			if(pseudoUserPartition != superUserPartition) {
				this.loginUserId = superUser.getId();				
				setUserPreferenceForPseudoUser(pseudoUser, superUserPartition);
			}
		} else {
			SuperUserPermissionAssistant.get().setSuperUser(superUser, null); //For new design
		}
		return true;
	}

	private void setUserPreferenceForPseudoUser(User pseudoUser, Partition superUserPartition) {
		UserPreferencesKey key = getKeyManager().getKey(pseudoUser, getIdName());
		boolean newKey = false;
		if(key == null) {
			key = getKeyManager().getKey(pseudoUser.getId(), getIdName());
			newKey = true;
		}				
		UserPreferences userPreferences = getRecordManager().getLatestRecord(key);
		if(userPreferences != null) {
			String partitionValue = superUserPartition.getPartitionCode();
			String partitionKey = "Partition";
			UserPreferencesData userPreferencesData = userPreferences.getData();
			userPreferencesData.setProperty(partitionKey, partitionValue);
			this.data = userPreferencesData;
		}
		create(key, newKey);
	}
	
}