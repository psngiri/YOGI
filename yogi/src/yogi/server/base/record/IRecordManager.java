package yogi.server.base.record;
/**
 * @author Vikram Vadavala
 *
 */
import yogi.base.Factory;
import yogi.base.Manager;
import yogi.base.Selector;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.base.key.IKey;
import yogi.server.base.purge.PurgeableManager;
import yogi.server.base.version.IVersion;

public abstract class IRecordManager<K extends IKey, V extends IVersion, T extends IRecord<K,V>> extends PurgeableManager<T> {

	protected IRecordManager() {
		super();
	}
	
	protected abstract OneToManyInverseRelationship<K, T> getKeyToRecordRelationShip();

	protected abstract OneToManyInverseRelationship<V, T> getVersionToRecordRelationShip();

	@Override
	protected void buildRelationships(T record) {
		this.buildRelationship(record.getKey(), record, getKeyToRecordRelationShip());
	    this.buildRelationship(record.getVersion(), record, getVersionToRecordRelationShip());		
	}

	@Override
	protected void deleteRelationships(T record) {
		this.deleteRelationship(record.getKey(), record, getKeyToRecordRelationShip());
		this.deleteRelationship(record.getVersion(), record, getVersionToRecordRelationShip());
	}
	
	public ImmutableList<T> getRecords(K key){
		return this.getRelationship(key, getKeyToRecordRelationShip());
	}
	
	public ImmutableList<T> getRecords(V version){
		return this.getRelationship(version, getVersionToRecordRelationShip());
	}
	
	protected void purge(Manager<K> keyManager, Factory<T> recordFactory)
	{
		Selector<T> selector = getPurgeSelector();
		purgeRecords(keyManager, recordFactory, selector);
	}

	private void purgeRecords(Manager<K> keyManager, Factory<T> recordFactory, Selector<T> selector) {
		for(V version:getAllVersions())
		{
			this.purgeRelationship(version, selector, getVersionToRecordRelationShip());
		}
		for(K key:keyManager.findAll())
		{
			this.purgeRelationship(key, selector, getKeyToRecordRelationShip());
		}
		super.purge(recordFactory);
	}

	protected abstract ImmutableList<V> getAllVersions();
	
	
}
