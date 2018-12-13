package yogi.server.base.purge;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;

public class PurgeableAssistant extends RelationshipAssistant<Purgeable> {
	private static PurgeableAssistant itsInstance = new PurgeableAssistant();

	private static OneToOneSimpleRelationship<Purgeable, Boolean> purgeRecordRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(Purgeable.class, Boolean.class, "PurgeRecord");

	public static PurgeableAssistant get() {
		return itsInstance;
	}
	
	public void markForpurge(Purgeable record)
	{
		this.setRelationship(record, purgeRecordRel, true);
	}
	
	public void UnMarkForpurge(Purgeable record)
	{
		this.setRelationship(record, purgeRecordRel, false);
	}
	
	public boolean isPurgeable(Purgeable record)
	{
		Boolean rtnValue = this.getRelationship(record, purgeRecordRel);
		return (rtnValue== null)? false: rtnValue;
	}
}
