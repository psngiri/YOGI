package yogi.server.gui.userpartition;

import yogi.base.app.ErrorReporter;
import yogi.base.relationship.RelationshipManager;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.user.User;

public class UserPartitionManager extends RelationshipManager<UserPartition> {
	private static UserPartitionManager itsInstance = new UserPartitionManager();
	private static UserPartitionCreator userPartitionCreator = new UserPartitionCreator();
	private static OneToManyInverseRelationship<User,UserPartition> userRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(User.class, UserPartition.class, "UserPartitions");  
	private static OneToManyInverseRelationship<Partition,UserPartition> partitionRel = RelationshipTypeFactory.get().createOneToManyInverseRelationship(Partition.class, UserPartition.class, "UserPartitions");  

	protected UserPartitionManager() {
		super();
	}

	public static UserPartitionManager get() {
		return itsInstance;
	}
	
	@Override
	protected void buildRelationships(UserPartition userPartition) {
		if(userPartition == null ) return;
		this.buildRelationship(userPartition.getUser(), userPartition, userRel);
		this.buildRelationship(userPartition.getPartition(), userPartition, partitionRel);
	}

	@Override
	protected void deleteRelationships(UserPartition userPartition) {
		this.buildRelationship(userPartition.getUser(), userPartition, userRel);
		this.buildRelationship(userPartition.getPartition(), userPartition, partitionRel);
	}

	public ImmutableList<UserPartition> getUserPartitions(User user){
		return this.getRelationship(user, userRel);
	} 
	
	public ImmutableList<UserPartition> getUserPartitions(Partition partition){
		return this.getRelationship(partition, partitionRel);
	} 
	
	public UserPartition getUserPartition(User user, Partition partition){
		for(UserPartition userPartion: getUserPartitions(user)){
			if(userPartion.getPartition() == partition) return userPartion;
		}
		return createUserPartition(user, partition);
	}
	
	private synchronized UserPartition createUserPartition(User user,Partition partition)
	{
		for(UserPartition userPartion: getUserPartitions(user)){
			if(userPartion.getPartition() == partition) return userPartion;
		}
		ErrorReporter.get().finest("Creating UserPartition ", user.getName());
		userPartitionCreator.setUser(user);
		if(partition!=null)
		userPartitionCreator.setPartition(partition);
		return UserPartitionFactory.get().create(userPartitionCreator);
	}


}