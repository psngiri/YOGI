package yogi.server.gui.record.base;

import yogi.base.Factory;
import yogi.base.Manager;
import yogi.base.relationship.RelationshipObject;
import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.action.Action;
import yogi.server.base.purge.PurgeableManager;

/**
 * @author Vikram Vadavala
 *
 */

public abstract class BaseRecordManager<K extends RelationshipObject, T extends BaseRecord<K>> extends PurgeableManager<T> {
	
	protected BaseRecordManager() {
		super();
	}
	
	protected abstract OneToManyInverseRelationship<K, T> getKeyToRecordRelationShip();
	
	@Override
	protected void buildRelationships(T record) {
		this.buildRelationship(record.getKey(), record, getKeyToRecordRelationShip());
	}

	@Override
	protected void deleteRelationships(T record) {
		this.deleteRelationship(record.getKey(), record, getKeyToRecordRelationShip());
	}
	
	public ImmutableList<T> getRecords(K key){
		return this.getRelationship(key, getKeyToRecordRelationShip());
	}
	
	public T getLatestRecord(K key){
	 return getLatestRecord(key, false);
	}
	
	public T getLatestRecord(K key, boolean includeCancel){
		ImmutableList<T> records = getRecords(key);
		if(records.isEmpty()) return null;
		T latestRecord = records.get(records.size()-1);
		if(!latestRecord.getAction().equals(Action.Cancel) || includeCancel)
			return latestRecord;
		else
			return null;
	}
	
	public T getRecord(K key,long timestamp, boolean includeCancel){
		ImmutableList<T> records = getRecords(key);
		if(records.isEmpty()) return null;
		for(int i = records.size()-1; i >= 0;i--){
			T record = records.get(i);
			if(record.getTimeStamp() <= timestamp){
				if(!record.getAction().equals(Action.Cancel) || includeCancel){
					return record;
				}else{
					return null;
				}
			}
		}
		return null;
	}		
	
	protected void purge(Manager<K> manager, Factory<T> recordFactory) {
		for(K key : manager.findAll())
		{
			this.purgeRelationship(key, getPurgeSelector(), getKeyToRecordRelationShip());
		}
		super.purge(recordFactory);
	}
	
	public abstract void purge();

}
