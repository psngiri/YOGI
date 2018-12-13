package yogi.server.gui.user.preferences.key;

import yogi.base.Factory;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.record.key.KeyCreator;
import yogi.server.gui.record.key.KeyManager;
import yogi.server.gui.record.key.KeyValidator;
import yogi.server.gui.user.User;
import yogi.server.gui.userpartition.UserPartition;

public class UserPreferencesKeyManager extends KeyManager<UserPreferencesKey> {
	
	public static final String MY_PREFERENCES = "MyPreferences";
	private static UserPreferencesKeyManager itsInstance = new UserPreferencesKeyManager();
	private static OneToManyInverseRelationship<UserPartition, UserPreferencesKey> userPreferencesKey = RelationshipTypeFactory.get().createOneToManyInverseRelationship(UserPartition.class, UserPreferencesKey.class,"UserPreferencesKeys");
	private static UserPreferencesKeyCreator userPreferencesKeyCreator = new UserPreferencesKeyCreator();

	protected UserPreferencesKeyManager() {
		super();
	}

	public static UserPreferencesKeyManager get() {
		return itsInstance;
	}


	public UserPreferencesKey getKey(User user)
	{
		return getKey(user,MY_PREFERENCES);
	}

	@Override
	public Factory<UserPreferencesKey> getFactory() {
		return UserPreferencesKeyFactory.get();
	}

	@Override
	public KeyCreator<UserPreferencesKey> getCreator() {
		return userPreferencesKeyCreator;
	}

	@Override
	protected OneToManyInverseRelationship<UserPartition, UserPreferencesKey> getUserPartionToKeyRelationShip() {
		return userPreferencesKey;
	}

	@Override
	protected UserPartition getUserPartition(User user) {
		return PartitionAssistant.get().getUserAnyPartition(user);
	}

	@Override
	protected KeyValidator<? super UserPreferencesKey> getValidator() {
		return new UserPreferencesKeyValidator();
	}
	
}
