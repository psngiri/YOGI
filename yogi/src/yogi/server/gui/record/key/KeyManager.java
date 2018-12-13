package yogi.server.gui.record.key;

import java.util.ArrayList;

import yogi.base.Factory;
import yogi.base.Selector;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.base.purge.PurgeableManager;
import yogi.server.gui.partition.Partition;
import yogi.server.gui.partition.PartitionAssistant;
import yogi.server.gui.user.User;
import yogi.server.gui.user.UserManager;
import yogi.server.gui.userpartition.UserPartition;
import yogi.server.gui.userpartition.UserPartitionManager;

public abstract class KeyManager<K extends Key> extends PurgeableManager<K> {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static ImmutableList EMPTY_LIST = new ImmutableList(new ArrayList(0));

	protected KeyManager() {
		super();
	}

	protected abstract OneToManyInverseRelationship<UserPartition, K> getUserPartionToKeyRelationShip();

	protected void buildRelationships(K key) {
		this.buildRelationship(UserPartitionManager.get().getUserPartition(key.getUser(), key.getPartition()), key, getUserPartionToKeyRelationShip());
	}
	
	protected void deleteRelationships(K key) {
		this.deleteRelationship(UserPartitionManager.get().getUserPartition(key.getUser(), key.getPartition()), key, getUserPartionToKeyRelationShip());
	}
	
	@SuppressWarnings("unchecked")
	public ImmutableList<K> getKeys(User user)
	{
		UserPartition userPartition = getUserPartition(user);
		if(userPartition == null) {
			
			return (ImmutableList<K>)EMPTY_LIST;
		}
		return this.getRelationship(userPartition, getUserPartionToKeyRelationShip());
	}

	@SuppressWarnings("unchecked")
	public ImmutableList<K> getKeys(User user, Partition partition)
	{
		UserPartition userPartition = UserPartitionManager.get().getUserPartition(user, partition);
		if(userPartition == null) {
			
			return (ImmutableList<K>)EMPTY_LIST;
		}
		return this.getRelationship(userPartition, getUserPartionToKeyRelationShip());
	}
	protected UserPartition getUserPartition(User user) {
		return PartitionAssistant.get().getUserPartition(user);
	}
	
	public K getKey(String userId, String name)
	{
		return getKey(UserManager.get().getUser(userId),name, null,true);
	}
	
	public K getKey(User user, String name)
	{
		return getKey(user,name, null ,false);
	}
	
	public K getKey(User user, String name, Partition partition) {
		return getKey(user,name, partition,false);
	}

	public K getKey(User user, String name, Partition partition, boolean createIfNeeded)
	{
		return getKey(user, name, partition, System.currentTimeMillis(), createIfNeeded);
	}
	
	public K getKey(User user, String name, Partition partition, long timestamp, boolean createIfNeeded)
	{
		K key=null;
		if(null==user || null==name) return key;
		name=name.trim();
		if(name.isEmpty()) return null;
		if(partition == null){
			partition = getUserPartition(user).getPartition();
		}
		ImmutableList<K> keysForUser = getKeys(user, partition);
		for(K tmpKey : keysForUser){
			if(tmpKey.getIdName().equals(name)) return tmpKey;
		}
		if(createIfNeeded) key = create(user, name, partition, timestamp);
		return key;
	}

	private synchronized K create(User user, String name, Partition partition, long timestamp) {
		ImmutableList<K> keysForUser = getKeys(user);
		for(K tmpKey : keysForUser){
			if(tmpKey.getIdName().equals(name)) return tmpKey;
		}
		KeyCreator<K> creator = getCreator();
		creator.setUser(user);
		creator.setIdName(name);
		creator.setCreateDate(timestamp);
		creator.setPartition(partition);
		Factory<K> factory = getFactory();
		return factory.create(creator, getValidator());
	}

	protected abstract KeyValidator<? super K> getValidator();

	public abstract Factory<K> getFactory();

	public abstract KeyCreator<K> getCreator();
	
	public void purge()
	{
		purgeKeys(getFactory(), getPurgeSelector());
	}

	private void purgeKeys(Factory<K> keyFactory, Selector<K> selector) {
		for(UserPartition userPartition : UserPartitionManager.get().findAll())
		{
			this.purgeRelationship(userPartition, selector, getUserPartionToKeyRelationShip());
		}
		super.purge(keyFactory);
	}

}
