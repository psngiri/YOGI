package yogi.server.gui.user.preferences;

import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.action.Action;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.record.RecordManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;

public class UserPreferencesManager extends RecordManager<UserPreferencesKey,UserPreferencesData,UserPreferences> {
	private static UserPreferencesManager itsInstance = new UserPreferencesManager();
	private static OneToManyInverseRelationship<UserPreferencesKey, UserPreferences> keyUserPreferences = RelationshipTypeFactory.get().createOneToManyInverseRelationship(UserPreferencesKey.class, UserPreferences.class,"UserPreferencesKeys");


	protected UserPreferencesManager() {
		super();
	}

	public static UserPreferencesManager get() {
		return itsInstance;
	}

	@Override
	protected void buildRelationships(UserPreferences record) {
		super.buildRelationships(record);
		if(record.getAction()!=Action.Cancel) {
			PartitionAssistant.get().setUserPartition(record);
		}
	}

	@Override
	protected OneToManyInverseRelationship<UserPreferencesKey, UserPreferences> getKeyToRecordRelationShip() {
		return keyUserPreferences;
	}

	public void purge() {
		super.purge(UserPreferencesKeyManager.get(), UserPreferencesFactory.get());
	}
	
	

}
