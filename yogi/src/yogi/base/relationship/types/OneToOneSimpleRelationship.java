package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;

public class OneToOneSimpleRelationship<F extends RelationshipObject, T>  extends OneToOneRelationship<F, T>{

	OneToOneSimpleRelationship(Class<F> from, Class<T> to,String name, boolean inherited) {
		super(from, to, name, false, null, inherited);
	}
	
}
