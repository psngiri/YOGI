package yogi.server.gui.partition;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.user.User;
import yogi.server.gui.user.preferences.UserPreferences;
import yogi.server.gui.user.preferences.UserPreferencesManager;
import yogi.server.gui.user.preferences.key.UserPreferencesKey;
import yogi.server.gui.user.preferences.key.UserPreferencesKeyManager;
import yogi.server.gui.userpartition.UserPartition;
import yogi.server.gui.userpartition.UserPartitionManager;


public class PartitionAssistant extends RelationshipAssistant<User>{
	private static PartitionAssistant itsInstance = new PartitionAssistant();
	private static OneToOneSimpleRelationship<User, UserPartition> userPartitionRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, UserPartition.class, "UserPartition");
	private static OneToOneSimpleRelationship<User, UserPartition> userAnyPartitionRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(User.class, UserPartition.class, "UserAnyPartition");

	protected PartitionAssistant() {
		super();
	}

	public static PartitionAssistant get() {
		return itsInstance;
	}

	public void setUserPartition(UserPreferences userPreferences){
		User user = userPreferences.getUser();
		UserPartition userPartition = UserPartitionManager.get().getUserPartition(user, getPartition(user));
		this.setRelationship(user, userPartitionRel, userPartition);
	}

	public UserPartition getUserPartition(User user){
		return  this.getRelationship(user, userPartitionRel);
		
	}
	
	public UserPartition getUserAnyPartition(User user){
		UserPartition rtnValue = this.getRelationship(user, userAnyPartitionRel);
		if(rtnValue == null){
			rtnValue = UserPartitionManager.get().getUserPartition(user, PartitionManager.ANY);
			this.setRelationship(user, userAnyPartitionRel, rtnValue);
		}
		return  rtnValue;
	}
	
	private Partition getPartition(User user){
		UserPreferencesKey key = UserPreferencesKeyManager.get().getKey(user);
		if(key == null) return PartitionManager.ANY;
		String partitionCode = UserPreferencesManager.get().getLatestRecord(key).getData().getProperty("Partition");
		return PartitionManager.get().getPartition(partitionCode);
	}
}
