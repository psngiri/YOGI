package yogi.server.gui.user.preferences.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserAssistant;
import yogi.server.gui.user.preferences.UserPreferencesData;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.userpartition.UserPartition;


public class UserPreferencesLevelAssistant {

	private static UserPreferencesLevelAssistant itsInstance = new UserPreferencesLevelAssistant();
	
	public static UserPreferencesData system = new UserPreferencesData();	
	public static HashMap<String, UserPreferencesData> levelUPD = new HashMap<String, UserPreferencesData>(2);
	
	public static UserPreferencesLevelAssistant get() {
		return itsInstance;
	}
	
	public UserPreferencesData getSystemUserPreferences(){
		return system;
	}
	
	public UserPreferencesLevel getUserPreferencesLevel(User user){
		List<UserPreferencesData> updList = new ArrayList<UserPreferencesData>(4);
		UserPreferencesData upd = null;
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(user);
		if(key == null) {
			upd = new UserPreferencesData();
			updList.add(upd);			
		} else {
			upd = UserPreferencesManager.get().getLatestRecord(key).getData();
			updList.add(upd);
			String carrier = UserAssistant.get().getCarrier(user);
			UserPartition userPartition = PartitionAssistant.get().getUserPartition(user);
			String partitionCode = userPartition.getPartition().getPartitionCode();
			UserPreferencesData carrierPartitionUpd = getUPD(carrier, partitionCode);
			updList.add(carrierPartitionUpd);
			UserPreferencesData carrierUpd = getUPD(carrier, "");
			updList.add(carrierUpd);
		}
		updList.add(system);
		return new UserPreferencesLevel(updList);
	}

	private UserPreferencesData getUPD(String carrier, String partitionCode) {
		return levelUPD.get(carrier + "/" + partitionCode);
	}
}