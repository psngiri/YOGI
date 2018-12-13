package yogi.server.gui.record.key.global;

import yogi.base.relationship.RelationshipAssistant;
import yogi.base.relationship.types.OneToOneSimpleRelationship;
import yogi.base.relationship.types.RelationshipTypeFactory;
import yogi.server.gui.record.key.Key;

public class GlobalAssistant extends RelationshipAssistant<Key> {
	private static GlobalAssistant itsInstance = new GlobalAssistant();
	private static OneToOneSimpleRelationship<Key, Boolean> globalRel = RelationshipTypeFactory.get().createOneToOneSimpleRelationship(Key.class, Boolean.class, "global");
	
	public static GlobalAssistant get(){
		return itsInstance;
	}
	
	public boolean isGlobal(Key key)
	{
		Boolean rtnValue = this.getRelationship(key, globalRel);
		if(rtnValue == null) return false;
		return rtnValue;
	}
	
	public void setGlobal(Key key)
	{
		this.setRelationship(key, globalRel, true);
	}
}
