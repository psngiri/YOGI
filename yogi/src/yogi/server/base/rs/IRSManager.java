package yogi.server.base.rs;

import yogi.base.relationship.types.OneToManyInverseRelationship;
import yogi.base.util.immutable.ImmutableList;
import yogi.server.base.key.IKey;
import yogi.server.base.purge.PurgeableManager;
import yogi.server.base.record.IRecord;
import yogi.server.base.version.IVersion;

/**
 * @author Vikram Vadavala
 *
 */

public abstract class IRSManager<K extends IKey,V extends IVersion, T extends IRecord<K,V>,R extends IRS<K,V,T>> extends PurgeableManager<R> {

	protected IRSManager() {
		super();
	}

	protected abstract OneToManyInverseRelationship<T, R> getRecordToRSRelationShip();

	protected void buildRelationships(R rs) {
		this.buildRelationship(rs.getRecord(), rs, getRecordToRSRelationShip());
	}
	
	protected void deleteRelationships(R rs) {
		this.deleteRelationship(rs.getRecord(), rs, getRecordToRSRelationShip());
	}
	
	public ImmutableList<R> getRepeatingSegments(T record)
	{
		return this.getRelationship(record, getRecordToRSRelationShip());
	}

	
}
