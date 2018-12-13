package yogi.base.relationship.types;

import yogi.base.relationship.RelationshipObject;


public class OneToOneInverseRelationship<F extends RelationshipObject, T> extends OneToOneRelationship<F,T> {

	OneToOneInverseRelationship(Class<F> from, Class<T> to, String name, boolean inherited) {
		super(from, to, name, true, null, inherited);
	}

}
